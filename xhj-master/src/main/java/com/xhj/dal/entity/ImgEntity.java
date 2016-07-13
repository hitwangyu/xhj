package com.xhj.dal.entity;

import java.util.Date;

import com.xhj.dal.base.BaseEntity;

public class ImgEntity implements BaseEntity {
  private Integer id;
  private String imgAddr;
  private String linkAddr;
  private Date createTime;
  private Integer type;
  private Integer weight;
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getImgAddr() {
    return imgAddr;
  }

  public void setImgAddr(String imgAddr) {
    this.imgAddr = imgAddr;
  }

  public String getLinkAddr() {
    return linkAddr;
  }

  public void setLinkAddr(String linkAddr) {
    this.linkAddr = linkAddr;
  }

  @Override
  public boolean checkField() {
    return false;
  }
}
