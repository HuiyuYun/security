package org.yunhuiyu.security.distributed.uaa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yunhuiyu.security.distributed.uaa.dto.PermissionDTO;
import org.yunhuiyu.security.distributed.uaa.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by: 云珲瑜
 * Date: 2020/1/31 10:59
 * Description:
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDTO getUserDetailByUsername(String username){
        String sql = "SELECT * FROM user WHERE username = ?";
        List<UserDTO> users = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDTO.class));
        if (users != null && users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    public List<String> getPermissionByUserId (String userId) {
        String sql = "SELECT * FROM permission WHERE id IN(" +
                "SELECT permission_id FROM role_permission WHERE role_id IN (" +
                "SELECT role_id FROM user_role WHERE user_id = ?))";

        List<PermissionDTO> permissions = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDTO.class));
        List<String> list = new ArrayList<String>();
        permissions.forEach( permissionDTO -> list.add(permissionDTO.getCode()));

        if (permissions != null) return list;
        else return null;
    }

}
