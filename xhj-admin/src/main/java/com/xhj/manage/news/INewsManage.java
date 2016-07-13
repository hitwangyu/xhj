package com.xhj.manage.news;

import java.util.List;

import com.xhj.dal.entity.NewsEntity;
import com.xhj.query.NewsQuery;

public interface INewsManage {

  public NewsEntity getById(Integer id);

  public List<NewsEntity> getListByQuery(NewsQuery query);

  public void deleteById(Integer id);

  public void add(NewsEntity news);

  public void update(NewsEntity news);

  public int getCountByQuery(NewsQuery query);

}
