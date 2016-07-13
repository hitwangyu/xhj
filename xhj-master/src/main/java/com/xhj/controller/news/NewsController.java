package com.xhj.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.controller.base.BaseController;
import com.xhj.param.news.NewsQueryParam;
import com.xhj.query.NewsQuery;
import com.xhj.service.news.INewsService;
import com.xhj.util.query.NewsQueryUtil;

@Controller
public class NewsController extends BaseController {
  @Autowired
  private INewsService newsService;

  @RequestMapping(value = {"/news/getNewsList"})
  @ResponseBody
  public RestResponse getNewsList() {
    return newsService.getNewsListGroupTopN(10);
  }

  @RequestMapping(value = {"/news/getNewsDetail"})
  @ResponseBody
  public RestResponse getNewsDetail(@RequestParam("id") Integer id) {
    return newsService.getNewsById(id);
  }

  @RequestMapping(value = {"/news/query"})
  @ResponseBody
  public RestResponse query(@RequestBody NewsQueryParam param) {
    param.checkParam();
    NewsQuery query = NewsQueryUtil.toQuery(param);
    return newsService.getListByQuery(query);
  }

}
