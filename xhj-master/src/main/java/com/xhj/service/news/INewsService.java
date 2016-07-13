package com.xhj.service.news;

import com.xhj.common.RestResponse;
import com.xhj.query.NewsQuery;

public interface INewsService {

  /**
   * 获取新闻列表，每类新闻各获取前几条
   * 
   * @param topN
   * @return created by wangyu on 2015年8月6日 下午2:06:34
   */
  public RestResponse getNewsListGroupTopN(Integer topN);

  public RestResponse getNewsById(Integer id);

  public RestResponse getListByQuery(NewsQuery query);

}
