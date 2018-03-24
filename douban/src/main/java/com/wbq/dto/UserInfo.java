package com.wbq.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户表的实体类
 */
public class UserInfo {
    private int id;
    @NotNull
    private String account;
    @NotNull
    private String password;
    @NotNull
    private String userName;
    private Date createDate;
    private int isFreeze;
    private int isRemember;
    @NotNull
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getIsFreeze() {
        return isFreeze;
    }

    public void setIsFreeze(int isFreeze) {
        this.isFreeze = isFreeze;
    }

    public int getIsRemember() {
        return isRemember;
    }

    public void setIsRemember(int isRemember) {
        this.isRemember = isRemember;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", createDate=" + createDate +
                ", isFreeze=" + isFreeze +
                ", isRemember=" + isRemember +
                ", email='" + email + '\'' +
                '}';
    }
}
