package com.boonya.springcloud.permission.user.provider.mapper;


import com.boonya.springcloud.beans.base.BaseMapper;
import com.boonya.springcloud.beans.permission.entity.EsUser;

public interface EsUserMapper extends BaseMapper {

    int deleteByPrimaryKey(Integer esId);

    int insert(EsUser record);

    int insertSelective(EsUser record);

    EsUser selectByPrimaryKey(Integer esId);

    int updateByPrimaryKeySelective(EsUser record);

    int updateByPrimaryKey(EsUser record);
}