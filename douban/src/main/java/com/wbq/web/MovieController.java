package com.wbq.web;

import com.wbq.dto.Collection;
import com.wbq.dto.DoubanComment;
import com.wbq.dto.Movie;
import com.wbq.dto.Summary;
import com.wbq.service.MovieService;
import com.wbq.util.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author wubiqin
 * @Date 2018-2-5 10:48
 * @Description 获得电影数据
 */
@RestController
public class MovieController {
    @Autowired
    public MovieService movieService;

    /**
     * 获得Top250豆瓣电影
     *
     * @return
     */
    @RequestMapping(value = "/douban/movie/top250", method = RequestMethod.POST)
    public List<Movie> getTop250Movies(@RequestBody Movie movie) {
        if (movie != null) {
            return movieService.getTop250Movies(movie.getMovieName().trim(), movie.getMovieType().trim());
        }
        //返回所有的top250电影
        return movieService.getTop250Movies("", "");
    }

    /**
     * 获得该电影的热门评论
     *
     * @param commentsUrl
     * @return
     */
    @RequestMapping(value = "/douban/movie/hotcomment", method = RequestMethod.GET)
    public List<DoubanComment> getHotComments(@RequestParam String commentsUrl) {
        if (commentsUrl.contains("?")) {
            String temp = commentsUrl.split("from=playing_poster")[0];
            return movieService.getMovieHotComments(temp.substring(0, temp.length() - 1));
        }
        return movieService.getMovieHotComments(commentsUrl);
    }

    /**
     * 获得电影简介
     *
     * @param movieName
     * @return
     */
    @RequestMapping(value = "/douban/movie/summary", method = RequestMethod.GET)
    public Summary getMovieSummary(@RequestParam String movieName) {
        return movieService.getMovieSummary(movieName);
    }

    /**
     * 获得电影五星好评分布
     *
     * @param movieName
     * @return
     */
    @RequestMapping(value = "/douban/movie/scorechart", method = RequestMethod.GET)
    public Summary getScoreCharts(@RequestParam String movieName) {
        return movieService.getScoreChart(movieName);
    }

    /**
     * 获得正在热映电影信息
     *
     * @param movie
     * @return
     */
    @RequestMapping(value = "/douban/movie/nowPlayingMovies", method = RequestMethod.POST)
    public List<Movie> getNowPlayingMovies(@RequestBody Movie movie) {
        if (movie != null) {
            List<Movie> movies = movieService.getNowPlayingMovies(movie.getMovieName().trim(), movie.getMovieType().trim());
            return movies;
        }
        return movieService.getNowPlayingMovies("", "");
    }

    /**
     * 获得将要上映电影信息
     *
     * @param movie
     * @return
     */
    @RequestMapping(value = "/douban/movie/commingMovies", method = RequestMethod.POST)
    public List<Movie> getCommingMovies(@RequestBody Movie movie) {
        if (movie != null) {
            return movieService.getCommingMovies(movie.getMovieName().trim(), movie.getMovieType().trim());
        }
        return movieService.getCommingMovies("", "");
    }

    /**
     * 收藏电影
     *
     * @param movieName
     * @return
     */
    @RequestMapping(value = "/douban/movie/collect", method = RequestMethod.GET)
    public ActionResult<String> collectMovie(@RequestParam String movieName) {
        ActionResult<String> result = new ActionResult<>();
        boolean success = movieService.addMovieToCollection(movieName);
        if (success) {
            result.setSuccess(true);
            result.setData(null);
            result.setMsg("收藏成功");
        } else {
            result.setSuccess(false);
            result.setData(null);
            result.setMsg("该电影已收藏");
        }
        return result;
    }

    /**
     * 获得收藏的电影
     *
     * @param collection
     * @return
     */
    @RequestMapping(value = "/douban/collection", method = RequestMethod.POST)
    public List<Collection> getCollectionByNameAndlabel(@RequestBody Collection collection) {
        return movieService.getAllCollectionMovie(collection.getName().trim(), collection.getLabel().trim());
    }

    /**
     * 更新收藏的标签
     *
     * @param name
     * @param label
     */
    @RequestMapping(value = "/douban/collection/update", method = RequestMethod.POST)
    public void updateCollection(@RequestParam String name, @RequestParam String label) {
        movieService.updateCollection(name, label.trim());
    }

    /**
     * 删除收藏的电影
     *
     * @param name
     */
    @RequestMapping(value = "/douban/collection/delete", method = RequestMethod.POST)
    public void delectCollectionByName(@RequestParam String name) {
        movieService.deleteColleciton(name);
    }

    /**
     * 通过电影名称 获得收藏的标签
     * @param name
     * @return
     */
    @RequestMapping(value = "/douban/collection/{name}",method = RequestMethod.GET)
    public Collection getCollectionByName(@PathVariable String name){
        return movieService.getCollectionByMovieName(name);
    }

}
