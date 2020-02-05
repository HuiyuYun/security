//package org.yunhuiyu.security.distributed.uaa.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.yunhuiyu.security.distributed.uaa.config.AjaxSecurity.*;
//
///**
// * Create by: 云珲瑜
// * Date: 2020/1/29 10:04
// * Description: spring security提供了用户名密码登录、退出、会话管理等认证功能，只需配置即可。
// * 安全配置的内容包括：用户信息、密码编码器、安全拦截机制。
// */
////@EnableWebSecurity //Spring-boot-starter自动装配机制，不需要该注解
//@Configuration
////方法授权
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    AjaxAuthenticationEntryPoint authenticationEntryPoint;  //  未登陆时返回 JSON 格式的数据给前端（否则为 html）
//
//    @Autowired
//    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）
//
//    @Autowired
//    AjaxAuthenticationFailureHandler authenticationFailureHandler;  //  登录失败返回的 JSON 格式数据给前端（否则为 html）
//
//    @Autowired
//    AjaxLogoutSuccessHandler logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）
//
//    @Autowired
//    AjaxAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
//
//    //密码编码器
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        //用的比较多的加密方式
//        return new BCryptPasswordEncoder();
//    }
//
//    //配置安全拦截机制
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .httpBasic()
//                .authenticationEntryPoint(authenticationEntryPoint)
//
//                .and()
//                .authorizeRequests()
//                // 所有/权限认证 的所有请求 都放行
//                .antMatchers("/user/**").permitAll()
//                .antMatchers("/auth/**").permitAll()
//                // swagger start
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/v2/api-docs").permitAll()
//
//                .anyRequest().authenticated()// 其他 url 需要身份认证
//
//                .and()
//                .formLogin()  // 开启表单登录
//                .loginProcessingUrl("/user/login")//登录成功或失败都跳转到此路径
//                .usernameParameter("username")//请求验证参数
//                .passwordParameter("password")//请求验证参数
//                .successHandler(authenticationSuccessHandler) // 登录成功
//                .failureHandler(authenticationFailureHandler) // 登录失败
//                .permitAll()
//
//                .and()
//                .logout()
//                .logoutSuccessHandler(logoutSuccessHandler)
//                .permitAll();
//
//        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//    }
//}
