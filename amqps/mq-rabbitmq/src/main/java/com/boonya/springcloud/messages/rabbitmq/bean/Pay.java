package com.boonya.springcloud.messages.rabbitmq.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Copyright: 2019-2021
 * @FileName: Pay.java
 * @Author: PJL
 * @Date: 2020/4/23 17:17
 * @Description: 支付类
 */
public class Pay implements Serializable {

    /**
     * 订单编号UUID
     */
    private String id;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 支付买家
     */
    private String buyUser;
    /**
     * 实际支付
     */
    private BigDecimal realPrice;
    /**
     * 交易时间
     */
    private Date dealTime;

    /**
     * 默认构造
     */
    public Pay() {
    }

    /**
     * 消息解析时默认需要构造函数存在
     */
    public Pay(String id, String productName, BigDecimal productPrice, String buyUser, BigDecimal realPrice, Date dealTime) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.buyUser = buyUser;
        this.realPrice = realPrice;
        this.dealTime = dealTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getBuyUser() {
        return buyUser;
    }

    public void setBuyUser(String buyUser) {
        this.buyUser = buyUser;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }
}
