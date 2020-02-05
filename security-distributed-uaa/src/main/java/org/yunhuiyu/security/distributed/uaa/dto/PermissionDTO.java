package org.yunhuiyu.security.distributed.uaa.dto;

/**
 * Create by: 云珲瑜
 * Date: 2020/1/31 15:37
 * Description:
 */
public class PermissionDTO {

    private String id;
    private String code;
    private String description;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
