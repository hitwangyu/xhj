package com.xhj.query;

import java.util.List;

import com.xhj.common.PageQuery;

public class ImgQuery extends PageQuery {
  private Integer id;
  private Integer type;
  private Integer weight;
  private String likeDescription; // %description%
  private List<Integer> idList;

  public Integer getWeight() {
    return weight;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

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

  public List<Integer> getIdList() {
    return idList;
  }

  public void setIdList(List<Integer> idList) {
    this.idList = idList;
  }

  public String getLikeDescription() {
    return likeDescription;
  }

  public void setLikeDescription(String likeDescription) {
    this.likeDescription = likeDescription;
  }
}
