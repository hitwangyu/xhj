package com.xhj.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.constant.NewsConstant.NewsStatus;
import com.xhj.controller.base.BaseController;
import com.xhj.param.news.NewsAddParam;
import com.xhj.param.news.NewsQueryParam;
import com.xhj.param.news.NewsUpdateParam;
import com.xhj.query.NewsQuery;
import com.xhj.service.news.INewsService;
import com.xhj.spec.NewsSpec;
import com.xhj.util.param.NewsParamUtil;
import com.xhj.util.query.NewsQueryUtil;

@Controller
public class NewsController extends BaseController {
  @Autowired
  private INewsService newsService;

  @RequestMapping(value = {"/news/query"})
  @ResponseBody
  public RestResponse query(@RequestBody NewsQueryParam param) {
    param.checkParam();
    NewsQuery query = NewsQueryUtil.toQuery(param);
    return newsService.getListByQuery(query);
  }

  /**
   * 获取所有新闻分类
   * 
   * @return created by wangyu on 2015年8月18日 下午1:22:21
   */
  @RequestMapping(value = {"/news/queryAllClass"})
  @ResponseBody
  public RestResponse queryAllClass() {
    return newsService.queryAllClass();
  }

  @RequestMapping(value = {"/news/delete"})
  @ResponseBody
  public RestResponse delete(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    return newsService.deleteById(id);
  }

  @RequestMapping(value = {"/news/add"})
  @ResponseBody
  public RestResponse add(@RequestBody NewsAddParam param) {
    param.checkParam();
    return newsService.add(NewsParamUtil.toSpec(param));
  }

  @RequestMapping(value = {"/news/queryById"})
  @ResponseBody
  public RestResponse queryById(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    return newsService.getById(id);
  }

  /**
   * 发布新闻
   * 
   * @param id
   * @return created by wangyu on 2015年8月24日 下午8:00:41
   */
  @RequestMapping(value = {"/news/online"})
  @ResponseBody
  public RestResponse online(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    NewsSpec spec = new NewsSpec();
    spec.setId(id);
    spec.setStatus(NewsStatus.EFFECTIVE.getCode());
    return newsService.update(spec);
  }

  /**
   * 下线新闻
   * 
   * @param id
   * @return created by wangyu on 2015年8月24日 下午8:00:41
   */
  @RequestMapping(value = {"/news/offline"})
  @ResponseBody
  public RestResponse offline(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    NewsSpec spec = new NewsSpec();
    spec.setId(id);
    spec.setStatus(NewsStatus.NO_EFFECTIVE.getCode());
    return newsService.update(spec);
  }

  @RequestMapping(value = {"/news/update"})
  @ResponseBody
  public RestResponse update(@RequestBody NewsUpdateParam param) {
    param.checkParam();
    return newsService.update(NewsParamUtil.toSpec(param));
  }
}
