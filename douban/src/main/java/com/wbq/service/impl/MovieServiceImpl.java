package com.wbq.service.impl;

import com.wbq.dao.MovieDao;
import com.wbq.dto.Collection;
import com.wbq.dto.DoubanComment;
import com.wbq.dto.Movie;
import com.wbq.dto.Summary;
import com.wbq.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author wubiqin
 * @Date 2018-2-5 13:07
 * @Description
 */
@Service("movieService")
public class MovieServiceImpl implements MovieService {
    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    public MovieDao movieDao;
    @Override
    public List<Movie> getTop250Movies(String movieName,String movieType) {
        logger.info("获得top250的电影 movieName："+movieName+" movieType： "+movieType);
        return movieDao.getTop250Movies(movieName,movieType);
    }

    @Override
    public List<DoubanComment> getMovieHotComments(String commentUrl) {
/*
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
*/      logger.info("获得电影的热门评论 commentUrl: "+commentUrl);
        if(!StringUtils.isEmpty(commentUrl)){
            List<DoubanComment> commentList=movieDao.getMovieHotComments(commentUrl);
            return commentList;
        }
        return null;
    }

    @Override
    public Summary getMovieSummary(String movieName) {
        if(!StringUtils.isEmpty(movieName)){
            Summary summary=movieDao.getMovieSummary(movieName);
            if(summary!=null){
                if(!StringUtils.isEmpty(summary.getImagePath())){
                    //获得海报的照片
                    String imageNum=summary.getImagePath().split("/")[7];
                    summary.setImagePath(imageNum);
                }
                if(!StringUtils.isEmpty(summary.getScreenwriter())){
                    //获得编剧
                    int len=summary.getScreenwriter().length();
                    String screenWriter=summary.getScreenwriter().substring(0,len-1);
                    summary.setScreenwriter(screenWriter);
                }
                if(!StringUtils.isEmpty(summary.getActor())){
                    //获得主演
                    int len=summary.getActor().length();
                    String actor=summary.getActor().substring(0,len-1);
                    summary.setActor(actor);
                }
                if(!StringUtils.isEmpty(summary.getType())){
                    //获得电影类型
                    int len=summary.getType().length();
                    String type=summary.getType().substring(0,len-1);
                    summary.setType(type);
                }
            }
           return summary;
        }
        return null;
    }

    @Override
    public Summary getScoreChart(String movieName) {
        logger.info("获得电影 ："+movieName+" 的好评率");
        return movieDao.getScoreChart(movieName);
    }

    @Override
    public List<Movie> getNowPlayingMovies(String movieName, String movieType) {
        return movieDao.getNowPlayingMovies(movieName,movieType);
    }

    @Override
    public List<Movie> getCommingMovies(String movieName, String movieType) {
        return movieDao.getCommingMovies(movieName,movieType);
    }

    @Override
    public boolean addMovieToCollection(String name) {
        Collection collection=movieDao.getCollectionByMovieName(name);
        if(collection!=null) return false;
        else if(collection==null){
            movieDao.addMovieToCollection(name);
            return true;
        }
        return false;
    }

    @Override
    public List<Collection> getAllCollectionMovie(String name, String label) {
        return movieDao.getAllCollectionMovie(name,label);
    }

    @Override
    public void updateCollection(String name, String label) {
        movieDao.updateCollection(name,label);
    }

    @Override
    public void deleteColleciton(String name) {
        movieDao.deleteColleciton(name);
    }

    @Override
    public Collection getCollectionByMovieName(String name) {
        return movieDao.getCollectionByMovieName(name);
    }
}
