package com.xhj.param.img;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class ImgAddParam implements BaseParam {

  private String imgAddr;

  private String linkAddr;

  private String typeName;

  private Integer weight;

  private String description;

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(imgAddr) || StringUtils.isBlank(typeName)
        || StringUtils.isBlank(description) || weight == null) {
      throw new BusinessException("参数非法");
    }
    if (StringUtils.isNotBlank(typeName)) {
      ImgType imgType = ImgType.getByName(typeName);
      if (imgType == null) {
        throw new BusinessException("参数非法");
      }
    }
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


}
