package com.apidoc.dao;

import com.apidoc.entity.ApidocParam;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * <p>
 * 文档参数信息 Mapper 接口
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-15
 */
public interface ApidocParamDao extends BaseMapper<ApidocParam> {

    /**
     * 通过actionId查询参数列表
     *
     * @param actionId
     * @return
     */
    @Select("select * from apidoc_param where actionId=#{actionId} and returnd=#{returnd}")
    List<ApidocParam> selectListByActionId(@Param("actionId") Integer actionId, @Param("returnd") boolean returnd);

    @Delete("delete from apidoc_param where actionId=#{actionId}")
    int deleteByActionId(Integer actionId);
}
