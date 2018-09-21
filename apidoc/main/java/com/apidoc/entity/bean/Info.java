package com.apidoc.entity.bean;

/**
 * 文档的基本信息 bean
 */
public class Info {
    private String title = "文档标题"; //标题
    private String description = "暂无描述"; //描述
    private String version = "1.0.0"; //版本

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}
