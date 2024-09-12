package com.boonya.springcloud.beans.base;

import com.boonya.springcloud.beans.permission.entity.EsUser;
import com.boonya.springcloud.utils.DateUtil;
import com.boonya.springcloud.utils.Tools;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: BaseBean
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @date 2018-12-14
 */
public class BaseBean implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1727605156609983257L;

    private Integer createUser;// 创建人

    private String createDate;// 创建时间

    private Integer updateUser;// 更新人

    private String updateDate;// 更新时间

    private String lastIp;// 最后登录ip

    private String lastAddress;// 最后登录地址

    private String lastLoginDate;// 最后登录时间

    private EsUser createUserObj;// 创建人

    private EsUser updateUserObj;// 更新人

    private Integer disabled;

    private Date modifyTime;// 修改时间

    private Integer modifier;// 修改人

    private Date createTime;// 创建时间

    private Integer creator;// 创建人

    @SuppressWarnings("unused")
    private String strModifyTime;// 修改日期格式化
    @SuppressWarnings("unused")
    private String strCreateTime;// 创建日期格式化

    private String modifierName;// 修改人名称
    private String creatorName;// 创建人名称
    private String esCorCode;// 组织编码

    public String getStrModifyTime() {
        if (modifyTime != null) {
            return DateUtil.getDateFormat(modifyTime);
        } else {
            return "";
        }
    }

    public String getStrCreateTime() {
        if (createTime != null) {
            return DateUtil.getDateFormat(createTime);
        } else {
            return "";
        }
    }

    public String getModifierName() {
        return modifierName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getCreateDate() {
        if (Tools.isEmpty(createDate)) {
            return "";
        }
        if (createDate.endsWith(".0")) {
            return Tools.isEmpty(createDate) ? "" : createDate.substring(0,
                createDate.length() - 2);
        }
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        if (Tools.isEmpty(updateDate)) {
            return "";
        }
        if (updateDate.endsWith(".0")) {
            return Tools.isEmpty(updateDate) ? "" : updateDate.substring(0,
                updateDate.length() - 2);
        }
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(String lastAddress) {
        this.lastAddress = lastAddress;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public EsUser getCreateUserObj() {
        return createUserObj;
    }

    public void setCreateUserObj(EsUser createUserObj) {
        this.createUserObj = createUserObj;
    }

    public EsUser getUpdateUserObj() {
        return updateUserObj;
    }

    public void setUpdateUserObj(EsUser updateUserObj) {
        this.updateUserObj = updateUserObj;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
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

    public String getEsCorCode() {
        return esCorCode;
    }

    public void setEsCorCode(String esCorCode) {
        this.esCorCode = esCorCode;
    }


}
