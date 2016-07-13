package com.xhj.controller.productClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.controller.base.BaseController;
import com.xhj.param.productClass.ProductClassAddParam;
import com.xhj.param.productClass.ProductClassUpdateParam;
import com.xhj.service.classification.IProductClassService;
import com.xhj.spec.ProductClassSpec;
import com.xhj.util.param.ProductClassParamUtil;

@Controller
public class ProductClassController extends BaseController {

  @Autowired
  private IProductClassService productClassService;

  @RequestMapping(value = {"/productClass/getProductClassTree"})
  @ResponseBody
  public RestResponse getProductClassTree() {
    return productClassService.getProductClassTree();
  }

  @RequestMapping(value = {"/productClass/queryAllPath"})
  @ResponseBody
  public RestResponse queryAllPath() {
    return productClassService.getAllProductClassPathByLevel(3);
  }

  @RequestMapping(value = {"/productClass/add"})
  @ResponseBody
  public RestResponse add(@RequestBody ProductClassAddParam param) {
    param.checkParam();
    return productClassService.add(ProductClassParamUtil.toSpec(param));
  }

  @RequestMapping(value = {"/productClass/delete"})
  @ResponseBody
  public RestResponse delete(@RequestParam("path") String path) {
    return productClassService.deleteByPath(path);
  }

  @RequestMapping(value = {"/productClass/update"})
  @ResponseBody
  public RestResponse update(@RequestBody ProductClassUpdateParam param) {
    param.checkParam();
    ProductClassSpec spec = ProductClassParamUtil.toSpec(param);
    return productClassService.update(spec);
  }
}
