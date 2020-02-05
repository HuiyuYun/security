package org.yunhuiyu.security.distributed.uaa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/1 17:14
 * Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //密码模式需要，认证管理器（就是WebSecurityConfig里面的拦截机制）
    @Autowired
    private AuthenticationManager authenticationManager;
    //授权码模式需要
    @Autowired
    private AuthorizationCodeServices codeServices;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 将客户端信息存储到数据库
     */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
//        ClientDetailsService service = new JdbcClientDetailsService(dataSource);
//        ((JdbcClientDetailsService)service).setPasswordEncoder(passwordEncoder);
        JdbcClientDetailsService service = new JdbcClientDetailsService(dataSource);
        service.setPasswordEncoder(passwordEncoder);
        return service;
    }

    /**
     * 客户端详情配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
//        clients.inMemory()
//                .withClient("c1")
//                .secret(new BCryptPasswordEncoder().encode("secret"))
//                .resourceIds("res1")//可以设置很多
//                .authorizedGrantTypes("authorization_code",
//                        "password", "client_credentials","implicit","refresh_token")//有5种授权方式
//                .scopes("all")//客户端可以访问的范围，更大
//                .autoApprove(false)
//                .redirectUris("http://www.baidu.com")
//        ;
    }

    /**
     * 令牌管理模式
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services = new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService);
        services.setTokenStore(tokenStore);
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);

        services.setSupportRefreshToken(true);
        services.setAccessTokenValiditySeconds(7200);
        services.setRefreshTokenValiditySeconds(259200);
        return services;
    }

    /**
     * 授权码服务
     */
    @Bean
    public AuthorizationCodeServices codeServices(DataSource dataSource){
//        return new InMemoryAuthorizationCodeServices();
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    /**
     * 令牌访问端点和服务，四种授权模式：密码模式，简化模式，授权码模式，客户端模式
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)//密码模式
                .authorizationCodeServices(codeServices)//授权码模式
                .tokenServices(tokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST)
        ;
    }

    /**
     * 令牌安全配置
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
         *  /oauth/authorize : 授权端点
         *  /oauth/token : 令牌端点
         *  /oauth/confirm_access : 用户确认授权提交端点
         *  /oauth/error : 授权服务错误信息端点
         *  /oauth/check_token : 用于资源服务访问的令牌解析端点
         *  /oauth/token_key : 提供公钥的端点，如果使用的是JWT令牌。
         *
         */
        security
                .tokenKeyAccess("permitAll()")//oauth/token_key不用登录就可以获取
                .checkTokenAccess("permitAll()")//oauth/check_token 需要验证token的合法性
                .allowFormAuthenticationForClients()//表单认证
        ;
    }

////    public static void main(String[] args) {
////        System.out.println(new BCryptPasswordEncoder().encode("secret"));
////    }

}