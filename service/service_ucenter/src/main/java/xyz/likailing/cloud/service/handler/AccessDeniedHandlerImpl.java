package xyz.likailing.cloud.service.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * @create 2023/3/19 14:07
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        R r = R.error().message("用户没有权限");
        String json = JSON.toJSONString(r);
        httpServletResponse.getWriter().println(json);
    }
}
