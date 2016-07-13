package com.xhj.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.constant.ProductConstant.ProStatus;
import com.xhj.controller.base.BaseController;
import com.xhj.param.product.ProductAddParam;
import com.xhj.param.product.ProductQueryParam;
import com.xhj.param.product.ProductUpdateParam;
import com.xhj.query.ProductQuery;
import com.xhj.service.product.IProductService;
import com.xhj.spec.ProductSpec;
import com.xhj.util.param.ProductParamUtil;
import com.xhj.util.query.ProductQueryUtil;

@Controller
public class ProductController extends BaseController {
  @Autowired
  private IProductService productService;

  /**
   * 获取某个分类下的产品列表
   * 
   * @param id
   * @return created by wangyu on 2015年8月8日 下午1:32:48
   */
  @RequestMapping(value = {"/product/query"})
  @ResponseBody
  public RestResponse query(@RequestBody ProductQueryParam param) {
    param.checkParam();
    ProductQuery query = ProductQueryUtil.toQuery(param);
    return productService.getListByQuery(query);
  }

  @RequestMapping(value = {"/product/delete"})
  @ResponseBody
  public RestResponse delete(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    return productService.deleteById(id);
  }

  /**
   * 商品上架
   * 
   * @param id
   * @return created by wangyu on 2015年8月24日 下午8:00:41
   */
  @RequestMapping(value = {"/product/online"})
  @ResponseBody
  public RestResponse online(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    ProductSpec spec = new ProductSpec();
    spec.setId(id);
    spec.setStatus(ProStatus.EFFECTIVE.getCode());
    return productService.update(spec);
  }

  /**
   * 商品下架
   * 
   * @param id
   * @return created by wangyu on 2015年8月24日 下午8:00:41
   */
  @RequestMapping(value = {"/product/offline"})
  @ResponseBody
  public RestResponse offline(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    ProductSpec spec = new ProductSpec();
    spec.setId(id);
    spec.setStatus(ProStatus.NO_EFFECTIVE.getCode());
    return productService.update(spec);
  }

  @RequestMapping(value = {"/product/add"})
  @ResponseBody
  public RestResponse add(@RequestBody ProductAddParam param) {
    param.checkParam();
    return productService.add(ProductParamUtil.toSpec(param));
  }

  @RequestMapping(value = {"/product/queryById"})
  @ResponseBody
  public RestResponse queryById(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    return productService.getById(id);
  }

  @RequestMapping(value = {"/product/update"})
  @ResponseBody
  public RestResponse update(@RequestBody ProductUpdateParam param) {
    param.checkParam();
    return productService.update(ProductParamUtil.toSpec(param));
  }
}
