package com.xhj.param.productClass;

import org.apache.commons.lang3.StringUtils;

import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class ProductClassAddParam implements BaseParam {

  private String name;
  private String parentPath;

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(name)) {
      throw new BusinessException("参数非法");
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getParentPath() {
    return parentPath;
  }

  public void setParentPath(String parentPath) {
    this.parentPath = parentPath;
  }

}
