package xyz.likailing.cloud.service.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.common.base.util.JwtInfo;
import xyz.likailing.cloud.common.base.util.JwtUtils;
import xyz.likailing.cloud.common.base.util.RedisCache;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.entity.LoginUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author 12042
 * @create 2023/3/18 18:53
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(StringUtils.isEmpty(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        if(!JwtUtils.checkJwtTToken(request)){
            throw new CloudException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }
        JwtInfo info = JwtUtils.getUserIdByJwtToken(request);
        //从redis中获取用户信息
        if (Objects.isNull(info)){
            throw new CloudException(ResultCodeEnum.LOGIN_ERROR);
        }
        String redisKey = "login:" + info.getId();
        LoginUserDetails loginUserDetails = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUserDetails)){
            throw new CloudException(ResultCodeEnum.LOGIN_ERROR);
        }
        //存入contextHolder
        //TODO
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUserDetails,null,loginUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request,response);
    }
}
