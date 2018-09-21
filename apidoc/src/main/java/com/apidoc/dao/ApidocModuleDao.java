package com.apidoc.dao;

import com.apidoc.entity.ApidocModule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * <p>
 * 文档模块信息 Mapper 接口
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-13
 */
public interface ApidocModuleDao extends BaseMapper<ApidocModule> {

    /**
     * 通过包名称查询
     *
     * @param packageName
     * @return
     */
    @Select("select * from apidoc_module where packageName=#{packageName}")
    Set<ApidocModule> selectByPackageName(String packageName);

    /**
     * 通过id查询classList
     *
     * @param moduleId
     * @return
     */
    @Select("select classList from apidoc_module where id =#{moduleId}")
    String findClassListById(Integer moduleId);
}
