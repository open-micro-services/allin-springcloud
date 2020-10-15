package com.boonya.springcloud.beans.permission.entity;

import java.util.Date;

public class EsUser {

    private Integer esId;

    private String esCorCode;

    private String esLoginName;

    private String esLoginPwd;

    private String esUsertRights;

    private String esUserNameCn;

    private String esEmail;

    private String esTel;

    private String esExtPhone;

    private String esMobile;

    private String esFax;

    private Integer esStatus;

    private String esRemark;

    private String esLastIp;

    private String esLastAddress;

    private Date esLastLogindate;

    private Date createTime;

    private Integer creator;

    private Date modifyTime;

    private Integer modifier;

    private Integer esRoleId;

    private String esUserType;

    public Integer getEsId() {
        return esId;
    }

    public void setEsId(Integer esId) {
        this.esId = esId;
    }

    public String getEsCorCode() {
        return esCorCode;
    }

    public void setEsCorCode(String esCorCode) {
        this.esCorCode = esCorCode == null ? null : esCorCode.trim();
    }

    public String getEsLoginName() {
        return esLoginName;
    }

    public void setEsLoginName(String esLoginName) {
        this.esLoginName = esLoginName == null ? null : esLoginName.trim();
    }

    public String getEsLoginPwd() {
        return esLoginPwd;
    }

    public void setEsLoginPwd(String esLoginPwd) {
        this.esLoginPwd = esLoginPwd == null ? null : esLoginPwd.trim();
    }

    public String getEsUsertRights() {
        return esUsertRights;
    }

    public void setEsUsertRights(String esUsertRights) {
        this.esUsertRights = esUsertRights == null ? null : esUsertRights.trim();
    }

    public String getEsUserNameCn() {
        return esUserNameCn;
    }

    public void setEsUserNameCn(String esUserNameCn) {
        this.esUserNameCn = esUserNameCn == null ? null : esUserNameCn.trim();
    }

    public String getEsEmail() {
        return esEmail;
    }

    public void setEsEmail(String esEmail) {
        this.esEmail = esEmail == null ? null : esEmail.trim();
    }

    public String getEsTel() {
        return esTel;
    }

    public void setEsTel(String esTel) {
        this.esTel = esTel == null ? null : esTel.trim();
    }

    public String getEsExtPhone() {
        return esExtPhone;
    }

    public void setEsExtPhone(String esExtPhone) {
        this.esExtPhone = esExtPhone == null ? null : esExtPhone.trim();
    }

    public String getEsMobile() {
        return esMobile;
    }

    public void setEsMobile(String esMobile) {
        this.esMobile = esMobile == null ? null : esMobile.trim();
    }

    public String getEsFax() {
        return esFax;
    }

    public void setEsFax(String esFax) {
        this.esFax = esFax == null ? null : esFax.trim();
    }

    public Integer getEsStatus() {
        return esStatus;
    }

    public void setEsStatus(Integer esStatus) {
        this.esStatus = esStatus;
    }

    public String getEsRemark() {
        return esRemark;
    }

    public void setEsRemark(String esRemark) {
        this.esRemark = esRemark == null ? null : esRemark.trim();
    }

    public String getEsLastIp() {
        return esLastIp;
    }

    public void setEsLastIp(String esLastIp) {
        this.esLastIp = esLastIp == null ? null : esLastIp.trim();
    }

    public String getEsLastAddress() {
        return esLastAddress;
    }

    public void setEsLastAddress(String esLastAddress) {
        this.esLastAddress = esLastAddress == null ? null : esLastAddress.trim();
    }

    public Date getEsLastLogindate() {
        return esLastLogindate;
    }

    public void setEsLastLogindate(Date esLastLogindate) {
        this.esLastLogindate = esLastLogindate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Integer getEsRoleId() {
        return esRoleId;
    }

    public void setEsRoleId(Integer esRoleId) {
        this.esRoleId = esRoleId;
    }

    public String getEsUserType() {
        return esUserType;
    }

    public void setEsUserType(String esUserType) {
        this.esUserType = esUserType == null ? null : esUserType.trim();
    }
}