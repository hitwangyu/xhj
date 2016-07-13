package com.xhj.service.news.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhj.common.QueryResult;
import com.xhj.common.RestResponse;
import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.constant.NewsConstant.NewsType;
import com.xhj.dal.entity.ImgEntity;
import com.xhj.dal.entity.NewsEntity;
import com.xhj.localCache.ImgCache;
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
  public RestResponse getNewsListGroupTopN(Integer topN) {
    Set<Integer> newsTypeSet = NewsType.getAllType();
    List<NewsEntity> newsList = newsManage.getNewsListGroupTopN(newsTypeSet, topN);
    Map<Integer, List<NewsSpec>> map = new HashMap<>();
    for (NewsEntity news : newsList) {
      NewsSpec spec = NewsUtil.toSpec(news);
      if (map.containsKey(news.getType())) {
        map.get(news.getType()).add(spec);
      } else {
        List<NewsSpec> specList = new LinkedList<NewsSpec>();
        specList.add(spec);
        map.put(news.getType(), specList);
      }
    }
    List<Object> result = new LinkedList<>(); // 所有数据
    for (NewsType type : NewsType.values()) {
      Map<String, Object> grid = new HashMap<>();
      grid.put("classification", type.getName());
      String[] imgForWjs = getImgForWjs(type);
      grid.put("imgAddr", imgForWjs[0]);
      grid.put("imgLinkAddr", imgForWjs[1]);
      grid.put("newsList", filterForWjs(map.get(type.getType()), type, topN));
      result.add(grid);
    }
    return RestResponse.successResponse(result);
  }

  /**
   * 文交所的新闻列表少展现几条
   * 
   * @param list
   * @return created by wangyu on 2015年8月7日 下午4:35:02
   */
  private List<NewsSpec> filterForWjs(List<NewsSpec> list, NewsType newsTypeEnum, int topN) {
    if (CollectionUtils.isEmpty(list)) {
      return list;
    }
    if (newsTypeEnum == NewsType.NJWJS || newsTypeEnum == NewsType.ZNWJS) {
      return list.subList(0, Math.min(list.size(), topN / 2));
    }
    return list;
  }

  /**
   * 文交所设置图片
   * 
   * @param spec created by wangyu on 2015年8月7日 上午11:51:38
   */
  private String[] getImgForWjs(NewsType newsTypeEnum) {
    String[] imgInfos = new String[2];
    if (newsTypeEnum == NewsType.NJWJS) {
      List<ImgEntity> imgList = ImgCache.getImgListByType(ImgType.INDEX_XW_NJWJS);
      if (CollectionUtils.isNotEmpty(imgList)) {
        imgInfos[0] = imgList.get(0).getImgAddr();
        imgInfos[1] = imgList.get(0).getLinkAddr();
      }
    } else if (newsTypeEnum == NewsType.ZNWJS) {
      List<ImgEntity> imgList = ImgCache.getImgListByType(ImgType.INDEX_XW_ZNWJS);
      if (CollectionUtils.isNotEmpty(imgList)) {
        imgInfos[0] = imgList.get(0).getImgAddr();
        imgInfos[1] = imgList.get(0).getLinkAddr();
      }
    }
    return imgInfos;
  }

  @Override
  public RestResponse getNewsById(Integer id) {
    NewsEntity news = newsManage.getById(id);
    return RestResponse.successResponse(NewsUtil.toSpec(news));
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
}
