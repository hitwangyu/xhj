package com.xhj.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 * 
 * @author Administrator
 *
 */
@Controller
public class PageController {

  @RequestMapping(value = {"/", "/index"})
  public String index() {
    return "index";
  }

  @RequestMapping(value = {"/productDetail"})
  public String productDetail() {
    return "product/product_detail";
  }

  @RequestMapping(value = {"/productListInClass"})
  public String productListInClass() {
    return "product/product_list_class";
  }

  @RequestMapping(value = {"/newsListInClass"})
  public String newsListInClass() {
    return "news/news_list_class";
  }

  @RequestMapping(value = {"/company"})
  public String company() {
    return "company/company";
  }

  @RequestMapping(value = {"/newsDetail"})
  public String newsDetail() {
    return "news/news_detail";
  }

}
