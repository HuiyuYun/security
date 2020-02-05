package org.yunhuiyu.security.distributed.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Create by: 云珲瑜
 * Date: 2020/1/31 18:12
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableFeignClients(basePackages = {"org.yunhuiyu.security.distributed.uaa"})
public class UAAServerApp {
    public static void main(String[] args) {
        SpringApplication.run(UAAServerApp.class, args);
    }
}
