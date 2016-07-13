package com.xhj.query;

import java.util.List;

import com.xhj.common.PageQuery;

public class ProductClassQuery extends PageQuery {
  private Integer id;
  private String name;
  private Integer parentId;
  private Integer depth;
  private String path;
  private String prePath; // path%
  private List<Integer> idList;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getParentId() {
    return parentId;
  }

  public void setParentId(Integer parentId) {
    this.parentId = parentId;
  }

  public Integer getDepth() {
    return depth;
  }

  public void setDepth(Integer depth) {
    this.depth = depth;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPrePath() {
    return prePath;
  }

  public void setPrePath(String prePath) {
    this.prePath = prePath;
  }

  public List<Integer> getIdList() {
    return idList;
  }

  public void setIdList(List<Integer> idList) {
    this.idList = idList;
  }

}
