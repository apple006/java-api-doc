package com.apidoc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 文档参数信息
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-15
 */
@TableName("apidoc_param")
public class ApidocParam extends Model<ApidocParam> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父参数id
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 描述
     */
    private String description;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 是否必须
     */
    private Boolean required;
    /**
     * 是否是返回值
     */
    private Boolean returnd;
    /**
     * 接口id
     */
    private Integer actionId;
    /**
     * 所属类名 父类名
     */
    private String pclassName;

    private List<ApidocParam> list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean getReturnd() {
        return returnd;
    }

    public void setReturnd(Boolean returnd) {
        this.returnd = returnd;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getPclassName() {
        return pclassName;
    }

    public void setPclassName(String pclassName) {
        this.pclassName = pclassName;
    }

    public List<ApidocParam> getList() {
        return list;
    }

    public void setList(List<ApidocParam> list) {
        this.list = list;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
