package com.wbq.service;

import com.wbq.dto.Collection;
import com.wbq.dto.DoubanComment;
import com.wbq.dto.Movie;
import com.wbq.dto.Summary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author wubiqin
 * @Date 2018-2-5 10:49
 * @Description
 */
public interface MovieService {
    /**
     * 获得豆瓣Top250电影
     *
     * @return
     */
    List<Movie> getTop250Movies(String movieName, String movieType);

    /**
     * 获得电影热门评论
     *
     * @param commentUrl
     * @return
     */
    List<DoubanComment> getMovieHotComments(String commentUrl);

    /**
     * 获得电影简介
     *
     * @param movieName
     * @return
     */
    Summary getMovieSummary(String movieName);

    /**
     * 获得好评率
     *
     * @param movieName
     * @return
     */
    Summary getScoreChart(String movieName);

    /**
     * 获得正在热映电影
     *
     * @param movieName
     * @param movieType
     * @return
     */
    List<Movie> getNowPlayingMovies(String movieName, String movieType);

    /**
     * 获得将要上映电影
     *
     * @param movieName
     * @param movieType
     * @return
     */
    List<Movie> getCommingMovies(String movieName, String movieType);

    /**
     * 收藏电影
     *
     * @param name
     */
    boolean addMovieToCollection(String name);

    /**
     * 获得所有的收藏电影
     *
     * @return
     */
    List<Collection> getAllCollectionMovie(String name, String label);

    /**
     * 更新收藏的电影的信息 标签
     *
     * @param name
     * @param label
     */
    void updateCollection(String name, String label);

    /**
     * 删除收藏的电影
     *
     * @param name
     */
    void deleteColleciton(String name);
    /**
     * 通过电影名称获得收藏的电影
     *
     * @param name
     * @return
     */
    Collection getCollectionByMovieName(String name);
}
