package com.boonya.springcloud.permission.user.provider.serice.impl;

import com.boonya.springcloud.beans.permission.entity.EsUser;
import com.boonya.springcloud.beans.permission.service.EsUserService;
import com.boonya.springcloud.permission.user.provider.mapper.EsUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EsUserServiceImpl
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @company: 上海势航网络科技有限公司
 * @date 2018-12-14
 */
@Service
public class EsUserServiceImpl implements EsUserService {

    @Autowired
    EsUserMapper esUserMapper;

    @Override
    public EsUser findById(Integer id) {
        return esUserMapper.selectByPrimaryKey(id);
    }
}
