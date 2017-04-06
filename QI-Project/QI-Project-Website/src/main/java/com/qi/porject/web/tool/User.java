package com.qi.porject.web.tool;

import com.qi.common.constants.i18n.VerifyConstants;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户
 *
 * @author DingYS
 */
public class User {

    private static final long serialVersionUID = 3438704908801230597L;

    private Long guid;

    @NotNull
    @Length(min = 5, max = 20, message = VerifyConstants.Length)
    private String userName;

    @Length(min = 5, max = 20, message = VerifyConstants.Length)
    private String passWord;

    @Length(min = 5, max = 20, message = VerifyConstants.Length)
    private String email;

    private String remark;

    private Long creator;

    private Date createTime;

    private String updater;

    private Date updateTime;

    public User() {
        super();
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}