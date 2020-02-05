package org.yunhuiyu.security.distributed.uaa.config.AjaxSecurity;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.yunhuiyu.security.distributed.uaa.common.utils.ResultUtils;
import org.yunhuiyu.security.distributed.uaa.result.ResponseResult;
import org.yunhuiyu.security.distributed.uaa.result.ResultCodeEnum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录失败
 */
@Configuration
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ResponseResult<Object> result = ResultUtils.fail(ResultCodeEnum.LOGIN_FAIL);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}