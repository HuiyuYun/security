package org.yunhuiyu.security.distributed.uaa.result;

/**
 * Create by: 云珲瑜
 * Date: 2020/2/2 11:45
 * Description: 自定义响应处理
 */
public enum  ResultCodeEnum {

    SUCCESS(200, "请求成功"),
    FAIL(400, "请求失败"),
    LOGIN_FAIL(401,"登录失败"),
    UNAUTHORIZED(402,"未认证"),
    NOT_FOUND(404,"接口不存在"),
    INTERNAL_SERVER_ERROR(500,"服务器错误");

    public int code;
    public String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
