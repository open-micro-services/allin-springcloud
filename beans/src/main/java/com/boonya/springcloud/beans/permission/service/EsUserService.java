package com.boonya.springcloud.beans.permission.service;

import com.boonya.springcloud.beans.permission.entity.EsUser;

/**
 * @ClassName: EsUserService
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-27 15:07
 */
public interface EsUserService {

    EsUser findById(Integer id);
}
