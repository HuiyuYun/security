package org.yunhuiyu.security.distributed.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yunhuiyu.security.distributed.order.model.UserDTO;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/2 19:53
 * Description:
 */
@RestController
public class OrderController {

    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1(){
        UserDTO userDTO = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDTO.getUsername() + "访问资源1";
    }

}
