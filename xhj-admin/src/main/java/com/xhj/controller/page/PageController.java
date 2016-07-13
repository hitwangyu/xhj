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

  @RequestMapping(value = {"/productManage"})
  public String productManage() {
    return "/product/product_manage";
  }

  @RequestMapping(value = {"/productAdd"})
  public String productAdd() {
    return "/product/product_add";
  }

  @RequestMapping(value = {"/productModify"})
  public String productModify() {
    return "/product/product_modify";
  }

  @RequestMapping(value = {"/newsManage"})
  public String newsManage() {
    return "/news/news_manage";
  }

  @RequestMapping(value = {"/newsAdd"})
  public String newsAdd() {
    return "/news/news_add";
  }

  @RequestMapping(value = {"/newsModify"})
  public String newsModify() {
    return "/news/news_modify";
  }

  @RequestMapping(value = {"/imgManage"})
  public String imgManage() {
    return "/img/img_manage";
  }

  @RequestMapping(value = {"/imgAdd"})
  public String imgAdd() {
    return "/img/img_add";
  }

  @RequestMapping(value = {"/imgModify"})
  public String imgModify() {
    return "/img/img_modify";
  }

  @RequestMapping(value = {"/productClassManage"})
  public String productClassManage() {
    return "/productClass/productClass_manage";
  }

  @RequestMapping(value = {"/productClassAdd"})
  public String productClassAdd() {
    return "/productClass/productClass_add";
  }

}
