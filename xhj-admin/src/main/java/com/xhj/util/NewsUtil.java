package com.xhj.util;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.xhj.constant.NewsConstant.NewsType;
import com.xhj.dal.entity.NewsEntity;
import com.xhj.spec.NewsSpec;

public class NewsUtil {
  public static SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd");

  public static NewsSpec toSpec(NewsEntity news) {
    NewsSpec spec = new NewsSpec();
    spec.setId(news.getId());
    spec.setTitle(news.getTitle());
    spec.setType(news.getType());
    spec.setTypeName(NewsType.getByCode(news.getType()).getName());
    spec.setContent(news.getContent());
    spec.setAuthor(news.getAuthor());
    spec.setStatus(news.getStatus());
    spec.setCreateTime(timeFormat.format(news.getCreateTime()));
    return spec;
  }

  public static NewsEntity toEntity(NewsSpec spec) {
    NewsEntity news = new NewsEntity();
    news.setStatus(spec.getStatus());
    news.setTitle(spec.getTitle());
    news.setType(spec.getType());
    news.setContent(spec.getContent());
    news.setAuthor(spec.getAuthor());
    return news;
  }

  public static List<NewsSpec> toSpecList(List<NewsEntity> newsList) {
    List<NewsSpec> specList = new LinkedList<>();
    for (NewsEntity news : newsList) {
      specList.add(toSpec(news));
    }
    return specList;
  }
}
