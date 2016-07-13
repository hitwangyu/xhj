package com.xhj.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.controller.base.BaseController;
import com.xhj.param.product.ProductQueryParam;
import com.xhj.query.ProductQuery;
import com.xhj.service.product.IProductService;
import com.xhj.util.query.ProductQueryUtil;

@Controller
public class ProductController extends BaseController {
  @Autowired
  private IProductService productService;

  /**
   * 主页获取产品列表
   * 
   * @return created by wangyu on 2015年8月8日 下午1:33:03
   */
  @RequestMapping(value = {"/product/getProductList"})
  @ResponseBody
  public RestResponse getProductList() {
    return productService.getProductListGroupTopN(4);
  }

  @RequestMapping(value = {"/product/getProductDetail"})
  @ResponseBody
  public RestResponse getProductDetail(@RequestParam(value = "id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    return productService.getProductDetailById(id);
  }

  /**
   * 获取某个分类下的产品列表
   * 
   * @param className 祖先分类名
   * @return created by wangyu on 2015年8月8日 下午1:32:48
   */
  @RequestMapping(value = {"/product/getProductListByClassName"})
  @ResponseBody
  public RestResponse getProductListByClassName(@RequestBody ProductQueryParam param) {
    param.checkParam();
    ProductQuery query = ProductQueryUtil.toQuery(param);
    return productService.getProductListByQuery(query);
  }
}
