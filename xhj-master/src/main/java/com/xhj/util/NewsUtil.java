package com.xhj.util;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.xhj.dal.entity.NewsEntity;
import com.xhj.spec.NewsSpec;

public class NewsUtil {
  public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static NewsSpec toSpec(NewsEntity news) {
    NewsSpec spec = new NewsSpec();
    spec.setId(news.getId());
    spec.setStatus(news.getStatus());
    spec.setTitle(news.getTitle());
    spec.setType(news.getType());
    spec.setContent(news.getContent());
    spec.setAuthor(news.getAuthor());
    spec.setCreateTime(timeFormat.format(news.getCreateTime()));
    return spec;
  }

  public static List<NewsSpec> toSpecList(List<NewsEntity> newsList) {
    List<NewsSpec> specList = new LinkedList<>();
    for (NewsEntity news : newsList) {
      specList.add(toSpec(news));
    }
    return specList;
  }

}
