package org.yunhuiyu.security.distributed.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/4 17:48
 * Description:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayServerApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApp.class, args);
    }

}
