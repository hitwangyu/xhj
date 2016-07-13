package com.xhj.param.news;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.NewsConstant.NewsType;
import com.xhj.exception.BusinessException;
import com.xhj.param.BaseParam;

public class NewsAddParam implements BaseParam {

  private String title;

  private String author; // 新闻作者

  private String content; // 新闻内容

  private String newsClassName; // 新闻类型

  @Override
  public void checkParam() {
    if (StringUtils.isBlank(title) || StringUtils.isBlank(content)
        || StringUtils.isBlank(newsClassName)) {
      throw new BusinessException("参数非法");
    }
    if (StringUtils.isNotBlank(newsClassName)) {
      NewsType newsType = NewsType.getByName(newsClassName);
      if (newsType == null) {
        throw new BusinessException("参数非法");
      }
    }
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getNewsClassName() {
    return newsClassName;
  }

  public void setNewsClassName(String newsClassName) {
    this.newsClassName = newsClassName;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


}
