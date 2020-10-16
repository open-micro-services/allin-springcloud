package com.boonya.springcloud.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Copyright: 2019-2021
 * @FileName: Token.java
 * @Author: PJL
 * @Date: 2020/4/20 14:36
 * @Description: Token会话凭证
 */
@Setter
@Getter
public class Token extends User implements Serializable {

    /**
     * 超时时长
     */
    private long timeout;
    /**
     * 创建时间戳
     */
    private long createTime;
    /**
     * Token标识UUID
     */
    private String uuid;

    /**
     * 默认构造
     */
    public Token() {
        super();
    }

    /**
     * 设置TOKEN
     *
     * @param userId
     * @param userName
     * @param realName
     * @param phoneNumber
     * @param orgId
     * @param orgCode
     * @param parentIds
     */
    public Token(String userId, String userName, String realName, String phoneNumber, String orgId, String orgCode, Object[] parentIds) {
        this.createTime = System.currentTimeMillis();
        this.uuid = java.util.UUID.randomUUID().toString();
        super.setUserId(userId);
        super.setUserName(userName);
        super.setRealName(realName);
        super.setPhoneNumber(phoneNumber);
        super.setOrgId(orgId);
        super.setOrgCode(orgCode);
        super.setParentIds(parentIds);
    }

    /**
     * 验证是否过期
     *
     * @return
     */
    public boolean isExpired() {
        return System.currentTimeMillis() - createTime > timeout;
    }
}
