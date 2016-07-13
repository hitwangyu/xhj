package com.xhj.controller.productClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.controller.base.BaseController;
import com.xhj.service.classification.IProductClassService;

@Controller
public class ProductClassController extends BaseController {

  @Autowired
  private IProductClassService productClassService;

  @RequestMapping(value = {"/productClass/queryAllPath"})
  @ResponseBody
  public RestResponse queryAllPath() {
    return productClassService.getAllProductClassPath();
  }

  @RequestMapping(value = {"/productClass/getProductClassTree"})
  @ResponseBody
  public RestResponse getProductClassTree() {
    return productClassService.getProductClassTree();
  }

}
