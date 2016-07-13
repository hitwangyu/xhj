package com.xhj.spec;


public class ImgSpec {
  private Integer id;
  private String imgAddr;
  private String linkAddr;
  private String description;
  private String typeName;
  private Integer type;
  private Integer weight;

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

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
