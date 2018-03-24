package com.wbq.service.impl;

import com.wbq.dao.AccountDao;
import com.wbq.dao.LoginLogDao;
import com.wbq.dto.Login;
import com.wbq.dto.LoginLog;
import com.wbq.dto.UserInfo;
import com.wbq.service.AccountService;
import com.wbq.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author wubiqin
 * @Date 2017-12-9 14:22
 * @Description
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    public AccountDao accountDao;
    @Autowired
    public LoginLogDao loginLogDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ActionResult<String> registerAccount(UserInfo userInfo) {
        ActionResult<String> actionResult=new ActionResult<>();
        if (userInfo != null && !"".equals(userInfo.getAccount())) {
            int count = accountDao.accountExist(userInfo.getAccount().trim());
            if (count == 0) {
                userInfo.setCreateDate(new Date());
                accountDao.registerAccount(userInfo);
                logger.info("注册账号: " + userInfo.getAccount() + " 成功 " + new Date());
                actionResult.setSuccess(true);
                actionResult.setMsg("注册账号: "+userInfo.getAccount()+" 成功");
                actionResult.setData(null);
            }
            if (count == 1) {
                logger.info("注册失败！账号: " + userInfo.getAccount() + " 已存在");
                actionResult.setSuccess(false);
                actionResult.setMsg("注册失败！账号: " + userInfo.getAccount() + " 已存在");
                actionResult.setData(null);
            }
        }
        return actionResult;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteAccount(String account) {
        if (!StringUtils.isEmpty(account)) {
            int count = accountDao.accountExist(account);
            if (count == 0) {
                logger.info("不存在账号：" + account + "  删除失败 ");
                return false;
            }
            if (count == 1) {
                accountDao.deleteAccount(account);
                logger.info("账号: " + account + " 已删除 " + new Date());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean login(Login login) {
        if (login != null && !StringUtils.isEmpty(login.getAccount())) {
            int count = accountDao.accountExist(login.getAccount());
            if (count == 0) {
                logger.info("不存在账号：" + login.getAccount());
                return false;
            } else {
                UserInfo userInfo = accountDao.getUserInfoByAccount(login.getAccount());
                if (userInfo != null && userInfo.getPassword().equals(login.getPassword())) {
                    logger.info("账号：" + login.getAccount() + " 登录成功 " + new Date());
                    LoginLog loginLog = new LoginLog();
                    loginLog.setAccount(login.getAccount());
                    loginLog.setLoginDate(new Date());
                    loginLogDao.insertLoginLog(loginLog);
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public List<UserInfo> getAccountListByAccountAndUserName(String account, String userName) {
        logger.info("查询用户列表" + "账号: " + account + " 用户名: " + userName);
        List<UserInfo> userList = accountDao.getAccountListByAccountAndUserName(account, userName);
        return userList;
    }

    @Override
    public UserInfo getUserInfoByAccount(String account) {
        UserInfo userInfo = null;
        if (!StringUtils.isEmpty(account)) {
            userInfo = accountDao.getUserInfoByAccount(account);
        }
        return userInfo;
    }


}
