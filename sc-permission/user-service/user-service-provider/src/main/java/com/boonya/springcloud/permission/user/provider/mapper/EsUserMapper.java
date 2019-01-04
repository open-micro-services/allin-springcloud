package com.boonya.springcloud.permission.user.provider.mapper;


import com.boonya.springcloud.beans.base.BaseMapper;
import com.boonya.springcloud.beans.permission.entity.EsUser;
import org.springframework.stereotype.Repository;

@Repository("esUserMapper")
public interface EsUserMapper extends BaseMapper<EsUser,Integer> {

    Integer deleteByPrimaryKey(Integer esId);

    Integer insert(EsUser record);

    Integer insertSelective(EsUser record);

    EsUser selectByPrimaryKey(Integer esId);

    Integer updateByPrimaryKeySelective(EsUser record);

    Integer updateByPrimaryKey(EsUser record);
}