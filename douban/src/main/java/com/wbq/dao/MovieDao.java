package com.wbq.dao;

import com.wbq.dto.Collection;
import com.wbq.dto.DoubanComment;
import com.wbq.dto.Movie;
import com.wbq.dto.Summary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author wubiqin
 * @Date 2018-2-5 11:22
 * @Description
 */
@Repository("movieDao")
public interface MovieDao {
    /**
     * 获得豆瓣Top250电影
     *
     * @return
     */
    List<Movie> getTop250Movies(@Param("movieName") String movieName, @Param("movieType") String movieType);

    /**
     * 获得正在热映电影
     *
     * @param movieName
     * @param movieType
     * @return
     */
    List<Movie> getNowPlayingMovies(@Param("movieName") String movieName, @Param("movieType") String movieType);

    /**
     * 获得将要上映电影
     *
     * @param movieName
     * @param movieType
     * @return
     */
    List<Movie> getCommingMovies(@Param("movieName") String movieName, @Param("movieType") String movieType);

    /**
     * 获得电影热门评论
     *
     * @param commentUrl
     * @return
     */
    List<DoubanComment> getMovieHotComments(@Param("commentUrl") String commentUrl);

    /**
     * 获得电影简介
     *
     * @param movieName
     * @return
     */
    Summary getMovieSummary(@Param("movieName") String movieName);

    /**
     * 获得好评率
     *
     * @param movieName
     * @return
     */
    Summary getScoreChart(@Param("movieName") String movieName);

    /**
     * 收藏电影
     *
     * @param name
     */
    void addMovieToCollection(String name);

    /**
     * 获得所有的收藏电影
     *
     * @return
     */
    List<Collection> getAllCollectionMovie(@Param("name") String name, @Param("label") String label);

    /**
     * 通过电影名称获得收藏的电影
     *
     * @param name
     * @return
     */
    Collection getCollectionByMovieName(String name);

    /**
     * 更新收藏的电影的信息 标签
     *
     * @param name
     * @param label
     */
    void updateCollection(@Param("name") String name, @Param("label") String label);

    /**
     * 删除收藏的电影
     * @param name
     */
    void deleteColleciton(String name);
}
