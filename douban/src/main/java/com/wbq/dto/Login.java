package com.wbq.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author 吴璧钦
 * @Date 2017-12-7 9:48
 * 登录信息
 */
public class Login {
    @NotNull
    @Size(min = 6, max = 16)
    private String account;//用户名
    @NotNull
    @Size(min = 6, max = 25)
    private String password;//密码

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
}
