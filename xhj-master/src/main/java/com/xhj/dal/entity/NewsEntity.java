package com.xhj.dal.entity;

import java.util.Date;

import com.xhj.constant.ProductConstant.ProStatus;
import com.xhj.dal.base.BaseEntity;

public class NewsEntity implements BaseEntity {
  private Integer id;

  private String title;

  private String author;

  private String content; // 新闻内容

  private Integer status;

  private Integer type;

  private Date createTime;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public boolean checkField() {
    return false;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setStatus(ProStatus status) {
    this.status = status.getCode();
  }
}
