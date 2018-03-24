package com.wbq.job;

import com.wbq.config.MybatisConfig;
import com.wbq.dao.MovieDao;
import com.wbq.dto.DoubanComment;
import com.wbq.dto.Movie;
import com.wbq.util.CiYunGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * @Author wubiqin
 * @Date 2018-2-7 14:39
 * @Description 定时任务 生成词云
 */
public class GeneratorCiYunJob {
    @Autowired
    public MovieDao movieDao;
    public void generatorCiyunComment() throws IOException {
        List<Movie> movies=movieDao.getTop250Movies("","");
        for(Movie movie:movies){
            if(movie!=null&& !StringUtils.isEmpty(movie.getCommentsUrl())){
                String name=movie.getCommentsUrl().split("/")[4];
                StringBuffer data=new StringBuffer();
                List<DoubanComment> comments=movieDao.getMovieHotComments(movie.getCommentsUrl());
                for(DoubanComment comment:comments){
                    if(comment!=null&&!StringUtils.isEmpty(comment.getComment())){
                        data.append(comment.getComment());
                    }
                }
                if(comments.size()>0){
                    //评论写入txt文件中
                    CiYunGenerator.createTxtFile(name,data.toString());
                }else{
                    CiYunGenerator.createTxtFile(name,"该影片没有评论");
                }
            }
        }
    }
}
