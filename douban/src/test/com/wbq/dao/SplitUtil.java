package com.wbq.dao;

public class SplitUtil {
    public static void main(String[] args) {
       String a= "https://movie.douban.com/subject/26004132/?from=playing_poster".split("from=playing_poster")[0];
       String b=a.substring(0,a.length()-1);
       System.out.print(b.toString());
    }
}
