package com.boonya.springcloud.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @Copyright: 2019-2021
 * @FileName: VerifyCode.java
 * @Author: PJL
 * @Date: 2020/5/19 10:00
 * @Description: 验证码
 */
@Setter
@Getter
public class VerifyCode implements Serializable {
    /**
     * 验证码失效时间
     */
    private final Long invalidInterval = 30 * 60 * 1000L;
    /**
     * 唯一编码
     */
    private String uniqueCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 编码对应的值
     */
    private String codeValue;

    /**
     * 构造函数
     *
     * @param uniqueCode
     * @param codeValue
     */
    public VerifyCode(String uniqueCode, String codeValue) {
        super();
        this.uniqueCode = uniqueCode;
        this.createTime = new Date();
        this.codeValue = codeValue;
    }

    /**
     * 判断当期注册吗是否到期
     *
     * @return
     */
    public boolean isInvalid() {
        Date current = new Date();
        if ((current.getTime() - this.createTime.getTime()) > this.invalidInterval) {
            return true;
        } else {
            return false;
        }
    }
}
