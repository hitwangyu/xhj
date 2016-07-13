package com.xhj.spec;

import java.util.List;

public class ProductSpec {
  private Integer id;

  private String name;

  private String price; // 100~200

  private String introduction;

  private String description;

  private String imgAddr;

  private String linkAddr;

  private String className;

  private Integer classId;

  private List<String> detailImgAddrList;

  private String imgIds;

  private Integer status;

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  private String createTime;

  public List<String> getDetailImgAddrList() {
    return detailImgAddrList;
  }

  public void setDetailImgAddrList(List<String> detailImgAddrList) {
    this.detailImgAddrList = detailImgAddrList;
  }


  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

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

  public Integer getClassId() {
    return classId;
  }

  public void setClassId(Integer classId) {
    this.classId = classId;
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
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

}
