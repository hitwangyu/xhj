package com.xhj.manage.news.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xhj.dal.entity.NewsEntity;
import com.xhj.dal.mapper.INewsDao;
import com.xhj.manage.news.INewsManage;
import com.xhj.query.NewsQuery;

@Component
public class NewsManageImpl implements INewsManage {
  @Autowired
  private INewsDao newsDao;

  @Override
  public NewsEntity getById(Integer id) {
    NewsQuery query = new NewsQuery();
    query.setId(id);
    List<NewsEntity> newsList = newsDao.query(query);
    return CollectionUtils.isEmpty(newsList) ? null : newsList.get(0);
  }

  @Override
  public List<NewsEntity> getNewsListGroupTopN(Set<Integer> newsTypeSet, Integer topN) {
    return newsDao.getNewsListGroupTopN(newsTypeSet, topN);
  }

  @Override
  public List<NewsEntity> getListByQuery(NewsQuery query) {
    return newsDao.query(query);
  }

  @Override
  public int getCountByQuery(NewsQuery query) {
    return newsDao.queryCount(query);
  }
}
