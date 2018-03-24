package com.wbq.dto;

import java.util.Date;

/**
 * 登录日志实体类
 */
public class LoginLog {

    private long id;
    private String account;
    private Date loginDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "LoginLogDao{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", loginDate=" + loginDate +
                '}';
    }
}
