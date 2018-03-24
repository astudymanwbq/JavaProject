package com.wbq.web;

import com.wbq.util.ImageCode;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;

/**
 * @Author wubiqin
 * @Date 2017-12-28 23:01
 * @Description
 */
@Controller
@RequestMapping("/image")
public class CheckCodeController {
    /**
     * 获得验证码
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping("imageCode")
    public String imageCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            OutputStream outputStream = response.getOutputStream();
            Map<String, Object> map = ImageCode.getImageCode(60, 20, outputStream);

            String validateImage = "validateImage";
            request.getSession().setAttribute(validateImage, map.get("strEnsure").toString().toLowerCase());
            request.getSession().setAttribute("codeTime", new Date().getTime());

            ImageIO.write((BufferedImage) map.get("image"), "JPEG", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return null;
    }

    /**
     * 验证验证码
     *
     * @param request
     * @param session
     * @return
     */
    @GetMapping("checkCode")
    @ResponseBody
    public String checkCode(HttpServletRequest request, HttpSession session) {
        String checkCode = request.getParameter("checkCode");
        Object checkObject = session.getAttribute("validateImage");
        if (checkObject == null) {
            return "验证码已失效，请重新输入!";
        }
        String captcha = checkObject.toString();
        Date now = new Date();
        Long codeTime = Long.valueOf(session.getAttribute("codeTime") + "");
        if (StringUtils.isEmpty(checkCode) || captcha == null || !(checkCode.equalsIgnoreCase(captcha))) {
            return "验证码错误";
        } else if ((now.getTime() - codeTime) / 1000 / 60 > 5) {
            /*验证码有效期为五分钟*/
            return "验证码失效,请重新输入";
        } else {
            /*验证成功，清除验证码*/
            session.removeAttribute("validateImage");
            return "1";
        }

    }
}
