package com.xhj.manage.news;

import java.util.List;
import java.util.Set;

import com.xhj.dal.entity.NewsEntity;
import com.xhj.query.NewsQuery;

public interface INewsManage {

  public NewsEntity getById(Integer id);

  public List<NewsEntity> getNewsListGroupTopN(Set<Integer> newsTypeSet, Integer topN);

  public List<NewsEntity> getListByQuery(NewsQuery query);

  public int getCountByQuery(NewsQuery query);

}
