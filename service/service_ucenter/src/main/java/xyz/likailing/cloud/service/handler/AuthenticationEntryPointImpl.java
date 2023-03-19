package xyz.likailing.cloud.service.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.service.base.exception.CloudException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 12042
 * @create 2023/3/19 14:03
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        R r = R.error().message("认证失败请重新登录");
        String json = JSON.toJSONString(r);
        httpServletResponse.getWriter().println(json);
    }
}
