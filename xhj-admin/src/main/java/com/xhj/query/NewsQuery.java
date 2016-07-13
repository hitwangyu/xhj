package com.xhj.query;

import com.xhj.common.PageQuery;

public class NewsQuery extends PageQuery {
  private Integer id;

  private String title;

  private String author; // 新闻来源

  private Integer type; // 新闻类型

  private String likeTitle; // %title%

  private Integer status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLikeTitle() {
    return likeTitle;
  }

  public void setLikeTitle(String likeTitle) {
    this.likeTitle = likeTitle;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

}
