package xyz.likailing.cloud.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.web.server.WebFilter;

import xyz.likailing.cloud.service.filter.XTAccountAuthentication;
import xyz.likailing.cloud.service.filter.XTAuthenticationConverter;

import java.util.Iterator;


/**
 * @Author 12042
 * @create 2023/3/18 10:37
 */
@EnableWebFluxSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private ServerAuthenticationEntryPoint authenticationEntryPoint;


    @Autowired
    XTAuthenticationConverter xtAuthenticationConverter;

    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http.authorizeExchange()
                .pathMatchers("/ucenter/user/login","/ucenter/user/register").permitAll()
                .pathMatchers("/swagger-ui.html",
                        "/swagger-ui/*",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/webjars/**").permitAll()
                .pathMatchers("/exp/webssh").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().permitAll()
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf()
                .disable()
                .logout().disable();
        SecurityWebFilterChain chain = http.build();
        Iterator<WebFilter> weIterable = chain.getWebFilters().toIterable().iterator();
        while(weIterable.hasNext()) {
            WebFilter f = weIterable.next();
            if(f instanceof AuthenticationWebFilter) {
                AuthenticationWebFilter webFilter = (AuthenticationWebFilter) f;
                //将自定义的AuthenticationConverter添加到过滤器中
                webFilter.setServerAuthenticationConverter(xtAuthenticationConverter);
            }
        }

        return chain;
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
        return new ReactiveAuthenticationManagerAdapter((authentication)->{
            if(authentication instanceof XTAccountAuthentication) {
                XTAccountAuthentication gmAccountAuthentication = (XTAccountAuthentication) authentication;
                if(gmAccountAuthentication.getPrincipal() != null) {
                    authentication.setAuthenticated(true);
                    return authentication;
                } else {
                    return authentication;
                }
            } else {
                return authentication;
            }
        });
    }

    /**
     * 配置密码加密器
     * 
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @author likailing
     * @create 2023/3/18
     **/
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("admin")
                .and()
                .withUser("student")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("student");
    }*/


}
