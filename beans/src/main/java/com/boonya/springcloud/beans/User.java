package com.boonya.springcloud.beans;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @Copyright: 2019-2021
 * @FileName: User.java
 * @Author: PJL
 * @Date: 2020/4/14 14:07
 * @Description: 用户信息【管理账号可以登录移动端】
 */
@Setter
@Getter
public class User implements Serializable {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 系统用户名
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 用户密码
     */
    private String pwd;

    /**
     * 组织机构ID
     */
    private String orgId;

    /**
     * 组织机构编码(编码规则可以利用DW_CODE)
     */
    private String orgCode;

    /**
     * 用户手机号码
     */
    private String phoneNumber;

    /**
     * 当前用户父级组织机构ID数组
     */
    private Object[] parentIds;

    /**
     * 用户是否启用
     */
    private String enableFlag;

    /**
     * 默认构造
     */
    public User() {

    }

    /**
     * 用户数据构造
     *
     * @param userId
     * @param userName
     * @param realName
     * @param pwd
     * @param orgId
     * @param orgCode
     * @param phoneNumber
     * @param parentIds
     */
    public User(String userId, String userName, String realName, String pwd, String orgId, String orgCode,
                String phoneNumber, Object[] parentIds, String enableFlag) {
        this.userId = userId;
        this.userName = userName;
        this.realName = realName;
        this.pwd = pwd;
        this.orgId = orgId;
        this.orgCode = orgCode;
        this.phoneNumber = phoneNumber;
        this.parentIds = parentIds;
        this.enableFlag = enableFlag;
    }
}
