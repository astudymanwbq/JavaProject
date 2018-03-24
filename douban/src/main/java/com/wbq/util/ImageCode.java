package com.wbq.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Author wubiqin
 * @Date 2017-12-28 23:17
 * @Description
 */
public class ImageCode {
    private static char mapTable[]={'0','1','2','3','4','5','6','7','8','9'};

    public static Map<String,Object> getImageCode(int width, int height, OutputStream outputStream){
        Map<String,Object> map=new HashMap<>();
        if(width<=0){
            width=60;
        }
        if(height<=0){
            height=20;
        }
        BufferedImage bufferedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        /*获取图像上下文*/
        Graphics graphics=bufferedImage.getGraphics();
        /*生成随机数*/
        Random random=new Random();
        /*设定背景色*/
        graphics.setColor(getRandColor(200,250));
        graphics.fillRect(0,0,width,height);
        /*设定字体*/
        graphics.setFont(new Font("Times New Roman",Font.PLAIN,18));
        /*随机产生干扰线*/
        graphics.setColor(getRandColor(160,200));
        for(int i=0;i<168;i++){
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            int x1=random.nextInt(12);
            int y1=random.nextInt(12);
            graphics.drawLine(x,y,x+x1,y+y1);
        }
        /*取随机生成的码*/
        String strEnsure="";
        /*生成四位验证码*/
        for(int i=0;i<4;i++){
            strEnsure+=mapTable[(int)(mapTable.length*Math.random())];
            /*将认证码显示在图像中*/
            graphics.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),
                    20+random.nextInt(110)));
            /*生成*/
            String validateCode=strEnsure.substring(i,i+1);
            graphics.drawString(validateCode,13*i+6,16);
        }
        /*释放图形上下文*/
        graphics.dispose();
        map.put("image",bufferedImage);
        map.put("strEnsure",strEnsure);
        return map;
    }
    /*给定范围获取随机数*/
    static Color getRandColor(int fc,int bc){
        Random random=new Random();
        if(fc>255){
            fc=255;
        }
        if(bc>255){
            bc=255;
        }
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
}
