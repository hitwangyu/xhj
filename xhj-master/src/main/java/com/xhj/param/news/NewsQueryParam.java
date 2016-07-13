package com.xhj.param.news;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.NewsConstant.NewsType;
import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class NewsQueryParam implements BaseParam {
  private String newsClassName;
  private String title;
  private Integer pageNo;
  private Integer pageSize;

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(newsClassName) && StringUtils.isBlank(title)) {
      throw new BusinessException("参数非法");
    }
    if (StringUtils.isNotBlank(newsClassName)) {
      NewsType newsType = NewsType.getByName(newsClassName);
      if (newsType == null) {
        throw new BusinessException("参数非法");
      }
    }
  }

  public String getNewsClassName() {
    return newsClassName;
  }

  public void setNewsClassName(String newsClassName) {
    this.newsClassName = newsClassName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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
