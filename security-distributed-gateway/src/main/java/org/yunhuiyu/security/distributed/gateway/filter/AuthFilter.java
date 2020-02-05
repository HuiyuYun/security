package org.yunhuiyu.security.distributed.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/4 20:49
 * Description: 拦截解析token为明文
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        //数字越小拦截等级越高
        return 0;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("进入网关拦截！");

        //请求上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //security上下文中获取认证授权的信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)) return null;
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
        //获取用户身份信息(name)
        Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();

        //获取用户权限（authorities放到List里面）
        List<String> authorizes = new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(c -> authorizes.add(((GrantedAuthority) c).getAuthority()));

        //将身份和权限解析放到http的header里面，转发给微服务
        OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
        Map<String, String> requestParameters = oAuth2Request.getRequestParameters();
        Map<String, Object> jsonToken = new HashMap<>(requestParameters);
        if (userAuthentication != null) {
            jsonToken.put("principal", userAuthentication.getName());
            jsonToken.put("authorities", authorizes);
        }
        currentContext.addZuulRequestHeader("json-token", JSON.toJSONString(jsonToken));

        return null;
    }
}
