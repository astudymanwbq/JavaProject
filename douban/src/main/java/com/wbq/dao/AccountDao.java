package com.wbq.dao;

import com.wbq.dto.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author wubiqin
 * @Date 2017-12-9 14:21
 * @Description
 */
@Repository("accountDao")
public interface AccountDao {
    /**
     * 注册账号
     *
     * @param userInfo
     * @return
     */
    void registerAccount(UserInfo userInfo);

    /**
     * 删除账号
     *
     * @param account 账号 逻辑删除 将is_del置1
     * @return
     */
    void deleteAccount(String account);

    /**
     * 通过账号得到用户信息
     *
     * @param account
     * @return
     */
    UserInfo getUserInfoByAccount(String account);

    /**
     * 通过account还有userName获得用户列表
     *
     * @param account
     * @param userName
     * @return
     */
    List<UserInfo> getAccountListByAccountAndUserName(@Param("account") String account,@Param("userName") String userName);

    /**
     * 该账号是否存在
     *
     * @param account
     * @return
     */
    int accountExist(String account);

}
