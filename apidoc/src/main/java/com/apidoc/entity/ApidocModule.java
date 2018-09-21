package com.apidoc.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 文档模块信息
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-13
 */
@TableName("apidoc_module")
public class ApidocModule extends Model<ApidocModule> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 模块名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer order;
    /**
     * 包名，区分不用的文档
     */
    private String packageName;
    /**
     * 类全名，多个之间用英文逗号隔开
     */
    private String classList;


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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassList() {
        return classList;
    }

    public void setClassList(String classList) {
        this.classList = classList;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ApidocModule{" +
                ", id=" + id +
                ", name=" + name +
                ", order=" + order +
                ", packageName=" + packageName +
                ", classList=" + classList +
                "}";
    }

    /**
     * 重写equals和hashCode算法 只要name相同则认为两个对象相同
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApidocModule)) return false;
        ApidocModule that = (ApidocModule) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
