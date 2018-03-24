package com.wbq.dto;

/**
 * 电影dto类
 */
public class Movie {

    private long id;
    private String movieName;
    private long ranking;
    private String score;
    private long scoreNum;
    private String img;
    private String commentsUrl;
    private String imagePath;
    private String movieType;
    private String area;
    private String wantWatchNum;
    private int movieNum;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }


    public long getRanking() {
        return ranking;
    }

    public void setRanking(long ranking) {
        this.ranking = ranking;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }


    public long getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(long scoreNum) {
        this.scoreNum = scoreNum;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getWantWatchNum() {
        return wantWatchNum;
    }

    public void setWantWatchNum(String wantWatchNum) {
        this.wantWatchNum = wantWatchNum;
    }

    public int getMovieNum() {
        return movieNum;
    }

    public void setMovieNum(int movieNum) {
        this.movieNum = movieNum;
    }
}
