package com.wbq.web;

import com.wbq.dto.Login;
import com.wbq.dto.UserInfo;
import com.wbq.service.AccountService;
import com.wbq.util.ActionResult;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Author wubiqin
 * @Date 2017-12-6 17:01
 */
@Controller
public class LoginController {
    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    public AccountService accountService;

    /**
     * 用户登录
     *
     * @param login
     * @return
     */
    @RequestMapping(value = "/douban/login", method = RequestMethod.GET)
    @ResponseBody
    public ActionResult<String> login(@Validated Login login, HttpServletRequest request) {
        ActionResult<String> result = new ActionResult<>();
        if (login != null) {
            request.getSession().setAttribute("account", login.getAccount());
            logger.info("账号:" + login.getAccount() + " 验证成功，进入主页面");
            result.setSuccess(true);
            result.setMsg("登录成功");
            result.setData("main");
            return result;
        }
        //登录失败，提示错误信息
        result.setSuccess(false);
        result.setMsg("登录失败，密码或账号错误");
        result.setData(null);
        return result;
    }

    /**
     * 获得用户个人信息
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/douban/userInfo", method = RequestMethod.GET)
    @ResponseBody
    public ActionResult<UserInfo> getUserInfo(HttpServletRequest request) {
        ActionResult<UserInfo> result = new ActionResult<>();
        String account = (String) request.getSession().getAttribute("account");
        if (account != null) {
            UserInfo userInfo = accountService.getUserInfoByAccount(account);
            if (userInfo != null) {
                result.setData(userInfo);
                result.setMsg("获取用户信息成功");
                result.setSuccess(true);
            } else {
                result.setData(null);
                result.setMsg("获取用户信息失败");
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/douban/page/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "login";
    }

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/douban/page/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * 跳转到主页面
     *
     * @return
     */
    @RequestMapping(value = "/douban/page/main", method = RequestMethod.GET)
    public String mainPage(HttpServletRequest request) {
        String account = (String) request.getSession().getAttribute("account");
        if (!StringUtils.isEmpty(account)) {
            return "main";
        }
        return "login";
    }

    /**
     * 注册账号
     *
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/douban/account/register", method = RequestMethod.POST)
    @ResponseBody
    public ActionResult<String> register(UserInfo userInfo) {
        return accountService.registerAccount(userInfo);
    }

    /**
     * 上传头像
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/douban/image/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        logger.info("开始文件上传");
        long beginTime = System.currentTimeMillis();
        try {
            FileUtils.writeByteArrayToFile(new File("D:/wubiqin/project/uploadFile" + new Date().getTime() + file.getOriginalFilename()), file.getBytes());
            long endTime = System.currentTimeMillis();
            logger.info("上传耗时:" + String.valueOf(endTime - beginTime) + " ms");
            String name = request.getParameter("name");
            return "success";
        } catch (IOException e) {
            logger.error("文件上传错误:" + e.getMessage());
            e.printStackTrace();
            return "fail";
        }
    }
}
