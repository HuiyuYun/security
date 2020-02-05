package org.yunhuiyu.security.distributed.order.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.yunhuiyu.security.distributed.order.model.UserDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/4 21:29
 * Description:
 */
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("进入服务端order拦截！");

        //获取到header里面的json-token
        String token = httpServletRequest.getHeader("json-token");
        if (token != null) {
            JSONObject jsonObject = JSON.parseObject(token);

            UserDTO userDTO = new UserDTO();
            String principal = jsonObject.getString("principal");
            userDTO.setUsername(principal);
            JSONArray authoritiesArray = jsonObject.getJSONArray("authorities");
            String[] authorities = authoritiesArray.toArray(new String[authoritiesArray.size()]);

            //将用户信息填充到用户token中
            UsernamePasswordAuthenticationToken UPToken =
                    new UsernamePasswordAuthenticationToken(userDTO, null, AuthorityUtils.createAuthorityList(authorities));
            UPToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            //填充到security上下文中
            SecurityContextHolder.getContext().setAuthentication(UPToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
