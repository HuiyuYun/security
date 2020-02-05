package org.yunhuiyu.security.distributed.gateway.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.yunhuiyu.security.distributed.gateway.filter.AuthFilter;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/4 21:22
 * Description:
 */
@Configuration
public class ZuulConfig {

    @Bean
    public AuthFilter preFilter(){
        return new AuthFilter();
    }

    /**
     * 过滤器注册
     */
    @Bean
    public FilterRegistrationBean corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setMaxAge(18000L);
        source.registerCorsConfiguration("/**",configuration);
        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean = new FilterRegistrationBean<>(corsFilter);
        corsFilterFilterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return corsFilterFilterRegistrationBean;

    }

}
