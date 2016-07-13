package com.xhj.param.product;

import org.apache.commons.lang3.StringUtils;

import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class ProductAddParam implements BaseParam {

  private String productName;
  private String productClassPath;
  private String imgIds; // 产品详情图片, 第一张图片的缩放作为产品列表小图
  private String price; // 100~200
  private String introduction; // 简介
  private String description; // 详情描述

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(productName) || StringUtils.isBlank(productClassPath)
        || StringUtils.isBlank(imgIds) || StringUtils.isBlank(price)
        || StringUtils.isBlank(introduction) || StringUtils.isBlank(description)) {
      throw new BusinessException("参数非法");
    }
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductClassPath() {
    return productClassPath;
  }

  public void setProductClassPath(String productClassPath) {
    this.productClassPath = productClassPath;
  }

  public String getImgIds() {
    return imgIds;
  }

  public void setImgIds(String imgIds) {
    this.imgIds = imgIds;
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

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

}
