package org.yunhuiyu.security.distributed.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Create by: 云珲瑜
 * Date: 2020/1/31 19:49
 * Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
//@EnableFeignClients(basePackages = {"org.yunhuiyu.security.distributed.order"})
public class OrderServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApp.class, args);
    }

}
