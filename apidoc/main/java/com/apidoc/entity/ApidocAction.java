package com.apidoc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 文档接口信息
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-14
 */
@TableName("apidoc_action")
public class ApidocAction extends Model<ApidocAction> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 模块id
     */
    private Integer moduleId;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 方法的唯一标示符，方法名-形参类型,形参类型
     * 为了区别java方法的重载
     */
    private String methodUUID;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 请求参数描述
     */
    private String requestDescription;

    /**
     * 响应参数描述
     */
    private String responseDescription;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getMethodUUID() {
        return methodUUID;
    }

    public void setMethodUUID(String methodUUID) {
        this.methodUUID = methodUUID;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    @Override
    public String toString() {
        return "ApidocAction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", moduleId=" + moduleId +
                ", order=" + order +
                ", methodUUID='" + methodUUID + '\'' +
                ", description='" + description + '\'' +
                ", requestDescription='" + requestDescription + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                '}';
    }

    /**
     * 重写equals和hashCode算法，只要methodUUID一致则认为两个action对象相同
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApidocAction)) return false;
        ApidocAction that = (ApidocAction) o;
        return Objects.equals(getMethodUUID(), that.getMethodUUID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMethodUUID());
    }
}
