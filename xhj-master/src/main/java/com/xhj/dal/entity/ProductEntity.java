package com.xhj.dal.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.xhj.constant.ProductConstant.ProStatus;
import com.xhj.dal.base.BaseEntity;

public class ProductEntity implements BaseEntity {
  private Integer id;

  private String name;

  private Integer classId;

  private String price; // 100~200

  private String introduction; // 简介

  private String description; // 详情描述

  private Date createTime;

  private String imgIds; // 产品详情图片列表

  private Integer status;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getClassId() {
    return classId;
  }

  public void setClassId(Integer classId) {
    this.classId = classId;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getFirstImgId() {
    String[] ids = imgIds.split(",");
    return Integer.valueOf(ids[0]);
  }

  public List<Integer> getImgIdList() {
    List<Integer> idList = new LinkedList<>();
    String[] ids = imgIds.split(",");
    for (String id : ids) {
      idList.add(Integer.valueOf(id));
    }
    return idList;
  }

  @Override
  public boolean checkField() {
    try {
      String[] ids = imgIds.split(",");
      for (String id : ids) {
        Integer.valueOf(id);
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getImgIds() {
    return imgIds;
  }

  public void setImgIds(String imgIds) {
    this.imgIds = imgIds;
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
