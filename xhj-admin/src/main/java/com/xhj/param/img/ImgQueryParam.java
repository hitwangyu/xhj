package com.xhj.param.img;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class ImgQueryParam implements BaseParam {
  private Integer id;
  private String typeName;
  private String description;
  private Integer pageNo = 1;
  private Integer pageSize = 20;

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(typeName) && StringUtils.isBlank(description) && id == null) {
      throw new BusinessException("参数非法");
    }
    if (StringUtils.isNotBlank(typeName)) {
      ImgType imgType = ImgType.getByName(typeName);
      if (imgType == null) {
        throw new BusinessException("参数非法");
      }
    }
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    if (pageNo > 0)
      this.pageNo = pageNo;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    if (pageSize > 0)
      this.pageSize = pageSize;
  }

}
