package com.wbq.dao;

import com.wbq.config.MybatisConfig;
import com.wbq.dto.Collection;
import com.wbq.dto.DoubanComment;
import com.wbq.dto.Movie;
import com.wbq.dto.Summary;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static com.wbq.util.CiYunGenerator.createTxtFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MybatisConfig.class})
public class MovieDaoTest {
    @Resource
    public MovieDao movieDao;

    /**
     * 生成正在上映电影的词云所需的txt文件
     *
     * @throws IOException
     */
    @Test
    public void generateCiYun() throws IOException {
        List<Movie> movieList = movieDao.getNowPlayingMovies("", "");
        List<DoubanComment> commentList = null;
        for (Movie movie : movieList) {
            if (movie.getCommentsUrl().contains("?")) {
                String temp = movie.getCommentsUrl().split("from=playing_poster")[0];
                commentList = movieDao.getMovieHotComments(temp.substring(0, temp.length() - 1));
            } else {
                commentList = movieDao.getMovieHotComments(movie.getCommentsUrl());
            }
            StringBuffer comment = new StringBuffer();
            for (DoubanComment doubanComment : commentList) {
                comment.append(doubanComment.getComment());
            }
            if (comment.length() > 0) {
                createTxtFile(String.valueOf(movie.getMovieNum()), comment.toString());
            } else {
                createTxtFile(String.valueOf(movie.getMovieNum()), "该电影没有评论");
            }
        }
    }

    @Test
    public void getTop250Movies() throws Exception {
        String name = "肖申克的救赎";
        String type = "剧情";
        List<Movie> movieList = movieDao.getTop250Movies("", "");
        System.out.println(movieList.toString());
    }

    @Test
    public void getMovieHotComments() throws Exception {
    }

    @Test
    public void getMovieSummary() throws Exception {
        String name = "肖申克的救赎";
        Summary summary = movieDao.getMovieSummary(name);
        System.out.print(summary.toString());
    }

    @Test
    public void getScoreChart() {
        String name = "肖申克的救赎";
        Summary summary = movieDao.getScoreChart(name);
        System.out.print(summary.toString());
    }

    @Test
    public void getNowPlayingMovies() {
        List<Movie> movies = movieDao.getNowPlayingMovies("", "");
        System.out.println("");
    }

    @Test
    public void getCommingMovies() {
        List<Movie> movies = movieDao.getCommingMovies("", "");
        System.out.println("");
    }
    @Test
    public void addMovieToCollection(){
        String name="test1";
        movieDao.addMovieToCollection(name);
    }
    @Test
    public void getAllCollection(){
        List<Collection> list=movieDao.getAllCollectionMovie("","");
        System.out.println(list.toString());
    }
    @Test
    public void getCollectionByName(){
        String name="test";
        Collection collection=movieDao.getCollectionByMovieName(name);
        System.out.println(collection);
    }
    @Test
    public void deleteCollectionByName(){
        String name="test";
        movieDao.deleteColleciton(name);
    }
    @Test
    public void updateCollection(){
        String name="test1";
        String label="lalala";

        movieDao.updateCollection(name,label);
    }
}