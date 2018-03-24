package com.wbq.dao;

import com.wbq.dto.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * @Author wubiqin
 * @Date 2017-12-10 19:01
 * @Description
 */
@Repository("loginLogDao")
public interface LoginLogDao {
    void insertLoginLog(LoginLog loginLog);
}
