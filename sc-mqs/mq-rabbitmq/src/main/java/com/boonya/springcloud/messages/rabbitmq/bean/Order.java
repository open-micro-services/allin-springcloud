package com.boonya.springcloud.messages.rabbitmq.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Copyright: 2019-2021
 * @FileName: Order.java
 * @Author: PJL
 * @Date: 2020/4/23 17:08
 * @Description: 订单类
 */
public class Order implements Serializable {

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
     * 运单号码
     */
    private String transferCode;
    /**
     * 折扣记录
     */
    private double discount;
    /**
     * 支付状态
     */
    private int payStatus;
    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 默认构造
     */
    public Order() {
    }

    /**
     * 消息解析时默认需要构造函数存在
     */
    public Order(String id, String productName, BigDecimal productPrice, String transferCode, double discount, int payStatus, Date orderTime) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.transferCode = transferCode;
        this.discount = discount;
        this.payStatus = payStatus;
        this.orderTime = orderTime;
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

    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}
