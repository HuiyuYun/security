package org.yunhuiyu.security.distributed.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/2 18:11
 * Description: 服务端拦截
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 拦截器
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //用的注解，所以用不着了
//                .antMatchers("/r/r1").hasAuthority("p1")
                .antMatchers("/r/**").authenticated()
                .anyRequest().permitAll()
        ;
    }
}
