package xyz.likailing.cloud.service.exp.interceptor;


import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import xyz.likailing.cloud.common.base.util.JwtInfo;
import xyz.likailing.cloud.common.base.util.JwtUtils;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.exp.constant.ConstantPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author 12042
 * @create 2023/4/9 23:11
 */
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
            //生成一个UUID，这里由于是独立的项目，没有用户模块，所以可以用随机的UUID
            //但是如果要集成到自己的项目中，需要将其改为自己识别用户的标识
            String token = request.getServletRequest().getParameter("token");
            if (StringUtils.isEmpty(token)){
                throw new CloudException("请先登录",20001);
            }
            JwtInfo jwtInfo = JwtUtils.getUserIdByJwtToken(token);
            String userId = jwtInfo.getId();
            //将uuid放到websocketsession中
            map.put(ConstantPool.USER_UUID_KEY, userId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
