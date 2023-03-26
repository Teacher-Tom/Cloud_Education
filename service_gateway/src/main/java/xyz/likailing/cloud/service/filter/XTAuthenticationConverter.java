package xyz.likailing.cloud.service.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerFormLoginAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import xyz.likailing.cloud.service.entity.LoginUserDetails;
import xyz.likailing.cloud.service.exception.CloudException;
import xyz.likailing.cloud.service.result.ResultCodeEnum;
import xyz.likailing.cloud.service.utils.JwtInfo;
import xyz.likailing.cloud.service.utils.JwtUtils;
import xyz.likailing.cloud.service.utils.RedisCache;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 12042
 * @create 2023/3/22 16:35
 */
/**
 * @description: 取出token中的权限信息
 * @author: ※狗尾巴草
 * @date: 2020-11-27 23:12
 **/
@Component
public class XTAuthenticationConverter extends ServerFormLoginAuthenticationConverter {
    private static Logger logger = LoggerFactory.getLogger(XTAuthenticationConverter.class);
    @Resource
    private RedisCache redisCache;  // 在filter中注入会为null

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {

        System.out.println("从token中获取登陆用户信息");
        //从token中获取登陆用户信息
        List<String> tokenList = exchange.getRequest().getHeaders().get("token");
        if(tokenList==null) {
            logger.error("用户认证信息为空,返回获取认证信息失败");
            return Mono.empty();
        } else {
            String token = tokenList.get(0);
            List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
            //获取权限信息
            if(!JwtUtils.checkJwtTToken(token)){
                logger.error("token无效");
                return Mono.error(new CloudException(ResultCodeEnum.INVALID_TOKEN));
            }
            JwtInfo info = JwtUtils.getUserIdByJwtToken(token);
            //从redis中获取用户信息
            if (Objects.isNull(info)){
                logger.error("token信息为空");
                return Mono.empty();
            }
            String redisKey = "login:" + info.getId();
            LoginUserDetails loginUserDetails = redisCache.getCacheObject(redisKey);
            if(Objects.isNull(loginUserDetails)){
                logger.error("没有找到登录信息");
                return Mono.empty();
            }
            List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) loginUserDetails.getAuthorities();

            //添加用户信息到spring security之中。
            XTAccountAuthentication  xinyueAccountAuthentication = new XTAccountAuthentication(null, token, authorities);
            return Mono.just(xinyueAccountAuthentication);
        }
    }
}


