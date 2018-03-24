package com.wbq.dto;

import java.util.Date;

/**
 * 评论
 */
public class DoubanComment {

  private String name;
  private Date date;
  private int star;
  private String comment;
  private String commentUrl;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }




  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  public String getCommentUrl() {
    return commentUrl;
  }

  public void setCommentUrl(String commentUrl) {
    this.commentUrl = commentUrl;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public int getStar() {
    return star;
  }

  public void setStar(int star) {
    this.star = star;
  }
}
