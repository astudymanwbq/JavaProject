package com.wbq.service;

import com.wbq.dto.Login;
import com.wbq.dto.UserInfo;
import com.wbq.util.ActionResult;

import java.util.List;

/**
 * @Author wubiqin
 * @Date 2017-12-9 14:17
 * @Description
 */
public interface AccountService {
    /**
     * 注册账号
     *
     * @param userInfo
     * @return
     */
    ActionResult<String> registerAccount(UserInfo userInfo);

    /**
     * 删除账号
     *
     * @param account 账号 逻辑删除 将is_del置1
     * @return
     */
    boolean deleteAccount(String account);

    /**
     * 登录账号
     *
     * @param login 账号 密码
     * @return 返回值为一时登录成功
     */
    boolean login(Login login);

    /**
     * 通过account还有userName获得信息
     *
     * @param account
     * @param userName
     * @return
     */
    List<UserInfo> getAccountListByAccountAndUserName(String account, String userName);

    /**
     * 通过账号得到用户信息
     *
     * @param account
     * @return
     */
    UserInfo getUserInfoByAccount(String account);

}
