package xyz.likailing.cloud.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import xyz.likailing.cloud.service.exception.CloudException;

/**
 * @Author 12042
 * @create 2023/3/19 14:03
 */
@Component
@Slf4j
public class AuthenticationEntryPointImpl implements ServerAuthenticationEntryPoint {


    @Override
    public Mono<Void> commence(ServerWebExchange serverWebExchange, AuthenticationException e) {
        e.printStackTrace();
        return Mono.error(new CloudException("认证失败请重新登录",20001));
    }
}
