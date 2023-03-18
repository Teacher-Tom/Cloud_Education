package xyz.likailing.cloud.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @Author 12042
 * @create 2023/3/18 10:46
 */
@Configuration
public class AccessTokenConfig {
    @Bean
    TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }
}
