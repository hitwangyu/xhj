package com.xhj.param.product;

import org.apache.commons.lang3.StringUtils;

import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class ProductQueryParam implements BaseParam {
  private String className;

  private Integer pageNo = 1;
  private Integer pageSize = 20;

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(className)) {
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


  public String getClassName() {
    return className;
  }


  public void setClassName(String className) {
    this.className = className;
  }
}
