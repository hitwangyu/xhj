package com.xhj.service.news.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhj.common.QueryResult;
import com.xhj.common.RestResponse;
import com.xhj.constant.NewsConstant.NewsType;
import com.xhj.dal.entity.NewsEntity;
import com.xhj.manage.news.INewsManage;
import com.xhj.query.NewsQuery;
import com.xhj.service.news.INewsService;
import com.xhj.spec.NewsSpec;
import com.xhj.util.LogFactory;
import com.xhj.util.LogFactory.Log;
import com.xhj.util.NewsUtil;

@Service
public class NewsServiceImpl implements INewsService {

  private final Log logger = LogFactory.getLog(NewsServiceImpl.class);

  @Autowired
  private INewsManage newsManage;

  @Override
  public RestResponse getById(Integer id) {
    return RestResponse.successResponse(NewsUtil.toSpec(newsManage.getById(id)));
  }

  @Override
  public RestResponse getListByQuery(NewsQuery query) {
    List<NewsEntity> newsList = newsManage.getListByQuery(query);
    if (CollectionUtils.isNotEmpty(newsList)) {
      query.setTotalSize(newsManage.getCountByQuery(query));
      query.calTotalPage();
    } else {
      query.setTotalSize(0);
      query.setTotalPage(1);
    }
    QueryResult<NewsQuery, NewsSpec> result = new QueryResult<>();
    result.setQueryCondition(query);
    result.setTarget(NewsUtil.toSpecList(newsList));
    return RestResponse.successResponse(result);
  }

  @Override
  public RestResponse deleteById(Integer id) {
    newsManage.deleteById(id);
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse add(NewsSpec spec) {
    NewsEntity news = NewsUtil.toEntity(spec);
    newsManage.add(news);
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse queryAllClass() {
    return RestResponse.successResponse(NewsType.getAllTypeName());
  }

  @Override
  public RestResponse update(NewsSpec spec) {
    NewsEntity news = newsManage.getById(spec.getId());
    if (news == null) {
      return RestResponse.errorResponse("新闻不存在");
    }
    updateEntity(news, spec);
    newsManage.update(news);
    return RestResponse.successResponse(true);
  }

  private void updateEntity(NewsEntity news, NewsSpec spec) {
    news.setId(spec.getId());
    if (spec.getStatus() != null) {
      news.setStatus(spec.getStatus());
    }
    if (StringUtils.isNotBlank(spec.getAuthor())) {
      news.setAuthor(spec.getAuthor());
    }
    if (StringUtils.isNotBlank(spec.getTitle())) {
      news.setTitle(spec.getTitle());
    }
    if (StringUtils.isNotBlank(spec.getContent())) {
      news.setContent(spec.getContent());
    }
    if (spec.getType() != null) {
      news.setType(spec.getType());
    }
  }
}
