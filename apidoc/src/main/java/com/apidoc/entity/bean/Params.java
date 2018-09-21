package com.apidoc.entity.bean;

import com.apidoc.entity.ApidocParam;

import java.util.List;

/**
 * 参数 请求参数和响应参数
 */
public class Params {
    private String type;//参数的请求或响应类型
    private String description;//描述
    private List<ApidocParam> params; //参数集合

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ApidocParam> getParams() {
        return params;
    }

    public void setParams(List<ApidocParam> params) {
        this.params = params;
    }
}
