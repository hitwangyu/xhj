package com.xhj.util.param;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.NewsConstant.NewsType;
import com.xhj.param.news.NewsAddParam;
import com.xhj.param.news.NewsUpdateParam;
import com.xhj.spec.NewsSpec;

public class NewsParamUtil {
  public static NewsSpec toSpec(NewsAddParam param) {
    NewsSpec spec = new NewsSpec();
    if (StringUtils.isNotBlank(param.getAuthor())) {
      spec.setAuthor(param.getAuthor());
    }
    spec.setTitle(param.getTitle());
    spec.setContent(param.getContent());
    if (StringUtils.isNotBlank(param.getNewsClassName())) {
      NewsType newsType = NewsType.getByName(param.getNewsClassName());
      if (newsType != null) {
        spec.setType(newsType.getType());
      }
    }
    return spec;
  }

  public static NewsSpec toSpec(NewsUpdateParam param) {
    NewsSpec spec = new NewsSpec();
    spec.setId(param.getId());
    if (StringUtils.isNotBlank(param.getAuthor())) {
      spec.setAuthor(param.getAuthor());
    }
    spec.setTitle(param.getTitle());
    spec.setContent(param.getContent());
    if (StringUtils.isNotBlank(param.getNewsClassName())) {
      NewsType newsType = NewsType.getByName(param.getNewsClassName());
      if (newsType != null) {
        spec.setType(newsType.getType());
      }
    }
    return spec;
  }
}
