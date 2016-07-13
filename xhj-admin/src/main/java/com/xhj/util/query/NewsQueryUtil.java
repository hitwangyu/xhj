package com.xhj.util.query;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.NewsConstant.NewsType;
import com.xhj.param.news.NewsQueryParam;
import com.xhj.query.NewsQuery;

public class NewsQueryUtil {
  public static NewsQuery toQuery(NewsQueryParam param) {
    NewsQuery query = new NewsQuery();
    if (StringUtils.isNotBlank(param.getNewsClassName())) {
      NewsType newsType = NewsType.getByName(param.getNewsClassName());
      if (newsType != null) {
        query.setType(newsType.getType());
      }
    }
    if (StringUtils.isNotBlank(param.getTitle())) {
      query.setLikeTitle(param.getTitle());
    }
    if (param.getPageNo() == null || param.getPageSize() == null) {
      query.setIsQueryAll(Boolean.TRUE);
    } else {
      query.setPageSize(param.getPageSize());
      query.setPageNo(param.getPageNo());
    }
    return query;
  }
}
