package org.yunhuiyu.security.distributed.uaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yunhuiyu.security.distributed.uaa.dao.UserDao;
import org.yunhuiyu.security.distributed.uaa.dto.UserDTO;

import java.util.List;

/**
 * Create by: 云珲瑜
 * Date: 2020/1/29 19:49
 * Description: 继承UserDetailsService后，security会用MyUserDetailsService
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //1.从内存中读取
//        return User.withUsername("zhangsan").password("123").authorities("p1").build();
        //2.从数据库中读取
        UserDTO userDTO = userDao.getUserDetailByUsername(s);
        //用户不存在返回null，这里不要抛异常，在provider统一处理
        if (userDTO == null) return null;
        //权限没有默认为null，必须至少传一个参数（数组）
        List<String> permissions = userDao.getPermissionByUserId(userDTO.getId());
        if (permissions == null) return null;
        String[] str = new String[permissions.size()];
        permissions.toArray(str);
        return User.withUsername(userDTO.getUsername()).password(userDTO.getPassword()).authorities(str).build();
    }

}
