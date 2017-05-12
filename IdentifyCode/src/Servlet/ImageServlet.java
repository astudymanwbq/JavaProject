package Servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by BBQ on 2017/4/25.
 */
public class ImageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //告知浏览当做图片处理
        response.setContentType("bufferedImage/jpeg");
        //设置不缓存图片
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","No-cache");
        response.setDateHeader("Expires",0);
        //定义bufferimage对象
        BufferedImage bufferedImage=new BufferedImage(68,22,BufferedImage.TYPE_INT_RGB);//创建图片
        //获得graphics对象
        Graphics graphics=bufferedImage.getGraphics();
        Random random=new Random();
        Color color=new Color(200,150,255);
        graphics.setColor(color);
        graphics.fillRect(0,0,68,22);

        char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int len=ch.length;
        int index;
        int height=80;
        int width=25;
        StringBuffer stringBuffer=new StringBuffer();//保存随机生成的字符
        // 填充干扰线
        for(int i=0; i<50; i++){
            graphics.setColor(new Color(random.nextInt(100)+155,random.nextInt(100)+155,random.nextInt(100)+155));
            graphics.drawLine(random.nextInt(width), random.nextInt(height),random.nextInt(width), random.nextInt(height));
        }
        // 绘制边框
        graphics.setColor(Color.GRAY);
        graphics.drawRect(0, 0, width-1, height-1);
        // 绘制验证码
        Font[] fonts = {new Font("隶书",Font.BOLD,18),new Font("楷体",Font.BOLD,18),new Font("宋体",Font.BOLD,18),new Font("幼圆",Font.BOLD,18)};
        for(int i=0;i<4;i++){
            //生成随机验证码
            index=random.nextInt(len);
            graphics.setFont(fonts[random.nextInt(fonts.length)]);
            graphics.setColor(new Color(random.nextInt(150),random.nextInt(150),random.nextInt(150)));
            graphics.drawString(ch[index]+"",(i*15)+3,18);
            stringBuffer.append(ch[index]);
        }
        HttpSession httpSession=request.getSession();
        httpSession.setAttribute("identifyCode",stringBuffer.toString());//将产生的验证码保存包session中,方便验证码的校验
        graphics.dispose();//释放graphics占用的资源
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(bufferedImage); //输出图片

    }
}
