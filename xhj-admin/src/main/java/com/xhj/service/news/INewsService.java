package com.xhj.service.news;

import com.xhj.common.RestResponse;
import com.xhj.query.NewsQuery;
import com.xhj.spec.NewsSpec;

public interface INewsService {

  public RestResponse getById(Integer id);

  public RestResponse getListByQuery(NewsQuery query);

  public RestResponse deleteById(Integer id);

  public RestResponse add(NewsSpec spec);

  public RestResponse queryAllClass();

  public RestResponse update(NewsSpec spec);

}
