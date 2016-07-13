package com.xhj.param.product;

import org.apache.commons.lang3.StringUtils;

import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class ProductQueryParam implements BaseParam {
  private String productName;

  private String productClassPath;

  private Integer pageNo = 1;
  private Integer pageSize = 20;

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

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(productName) && StringUtils.isBlank(productClassPath)) {
      throw new BusinessException("参数非法");
    }
  }


  public Integer getPageNo() {
    return pageNo;
  }


  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }


  public Integer getPageSize() {
    return pageSize;
  }


  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }
}
