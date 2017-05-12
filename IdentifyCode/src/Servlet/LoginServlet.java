package Servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by BBQ on 2017/4/25.
 */
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String identifyCode=(String)request.getSession().getAttribute("identifyCode");
        String inputCode=request.getParameter("inputCode");
        response.setContentType("text/html;charset=gbk");
        PrintWriter out=response.getWriter();
        if(inputCode.equalsIgnoreCase(identifyCode)){
            out.print("验证成功!");
        }else{
            out.print("验证失败!");
        }
        out.flush();
        out.close();//关闭输出流
    }
}
