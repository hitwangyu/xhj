package com.xhj.param.productClass;

import org.apache.commons.lang3.StringUtils;

import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class ProductClassUpdateParam implements BaseParam {

  private String name;
  private String path;

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(name) || StringUtils.isBlank(path)) {
      throw new BusinessException("参数非法");
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

}
