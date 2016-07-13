package com.xhj.query;

import java.util.List;

import com.xhj.common.PageQuery;


public class ProductQuery extends PageQuery {
  private Integer id;

  private String name;

  private String likeName;

  private String ancestorClassName; // 祖先分类名

  private String className;

  private String prePath;

  private Integer classId;

  private List<Integer> classIdList;

  private Integer status;

  public List<Integer> getClassIdList() {
    return classIdList;
  }

  public void setClassIdList(List<Integer> classIdList) {
    this.classIdList = classIdList;
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

  public Integer getClassId() {
    return classId;
  }

  public void setClassId(Integer classId) {
    this.classId = classId;
  }

  public String getLikeName() {
    return likeName;
  }

  public void setLikeName(String likeName) {
    this.likeName = likeName;
  }

  public String getPrePath() {
    return prePath;
  }

  public void setPrePath(String prePath) {
    this.prePath = prePath;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getAncestorClassName() {
    return ancestorClassName;
  }

  public void setAncestorClassName(String ancestorClassName) {
    this.ancestorClassName = ancestorClassName;
  }

}
