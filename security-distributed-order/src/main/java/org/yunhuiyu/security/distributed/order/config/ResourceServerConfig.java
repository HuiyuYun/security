package org.yunhuiyu.security.distributed.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/2 19:56
 * Description: 资源服务
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    //资源id
    public static final String RESOURCE_ID = "res1";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)
//                .tokenServices(tokenServices())//验证令牌的服务，JWT不需要这个了
                .stateless(true)
        ;
    }

    /**
     * 资源服务令牌解析服务(远程调用)
     */
//    @Bean
//    public ResourceServerTokenServices tokenServices(){
//        //使用远程服务请求授权服务器校验token，必须指定校验token的url、client_id、client_secret
//        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
//        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:8080/uaa/oauth/check_token");
//        remoteTokenServices.setClientId("c1");
//        remoteTokenServices.setClientSecret("secret");
//        return remoteTokenServices;
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //网关已经拦截过了
        http
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
//                .authorizeRequests()
//                //和授权服务一一对应
//                .antMatchers("/**").access("#oauth2.hasScope('ROLE_API')")
//                .and()
//                .csrf().disable()
//                //基于token，所以不用session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }
}