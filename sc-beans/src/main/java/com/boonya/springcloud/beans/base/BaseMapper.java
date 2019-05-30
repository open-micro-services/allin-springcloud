package com.boonya.springcloud.beans.base;

import java.util.List;

/**
 * mybatis基础mapper接口类
 *
 * @author pengjunlin
 * @packge com.wlyd.fmcgwms.util.base.BaseMapper
 * @date 2016年4月28日 下午2:22:02
 * @comment 添加注释
 * @update
 */
public interface BaseMapper<T, PK extends java.io.Serializable> {

    // -----------mybatis自动生成代码对应的方法名---------

    /**
     * 根据实体主键ID查询对象
     *
     * @param modelPK
     * @return
     * @throws
     * @MethodName: selectByPrimaryKey
     * @Description:
     */
    T selectByPrimaryKey(PK modelPK);

    /**
     * 根据实体主键ID删除对象
     *
     * @param modelPK
     * @return
     * @throws
     * @MethodName: deleteByPrimaryKey
     * @Description:
     */
    Integer deleteByPrimaryKey(PK modelPK);

    /**
     * 选择性地插入实体对象
     *
     * @param model
     * @return
     * @throws
     * @MethodName: insertSelective
     * @Description:
     */
    Integer insertSelective(T model);

    /**
     * 插入实体对象
     *
     * @param model
     * @return
     * @throws
     * @MethodName: insert
     * @Description:
     */
    Integer insert(T model);

    /**
     * 选择性地更新实体对象
     *
     * @param model
     * @return
     * @throws
     * @MethodName: updateByPrimaryKeySelective
     * @Description:
     */
    Integer updateByPrimaryKeySelective(T model);

    /**
     * 根据主键更新实体对象
     *
     * @param modelPK
     * @return
     * @throws
     * @MethodName: updateByPrimaryKey
     * @Description:
     */
    Integer updateByPrimaryKey(PK modelPK);

}
