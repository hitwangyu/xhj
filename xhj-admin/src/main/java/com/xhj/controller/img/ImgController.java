package com.xhj.controller.img;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.controller.base.BaseController;
import com.xhj.param.img.ImgAddParam;
import com.xhj.param.img.ImgQueryParam;
import com.xhj.param.img.ImgUpdateParam;
import com.xhj.query.ImgQuery;
import com.xhj.service.img.IImgService;
import com.xhj.util.param.ImgParamUtil;
import com.xhj.util.query.ImgQueryUtil;

@Controller
public class ImgController extends BaseController {
  @Autowired
  private IImgService imgService;

  @RequestMapping(value = {"/img/query"})
  @ResponseBody
  public RestResponse query(@RequestBody ImgQueryParam param) {
    param.checkParam();
    ImgQuery query = ImgQueryUtil.toQuery(param);
    return imgService.getListByQuery(query);
  }

  @RequestMapping(value = {"/img/queryById"})
  @ResponseBody
  public RestResponse queryById(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    return imgService.getById(id);
  }

  /**
   * 获取所有图片分类
   * 
   * @return created by wangyu on 2015年8月18日 下午1:22:21
   */
  @RequestMapping(value = {"/img/queryAllTypeName"})
  @ResponseBody
  public RestResponse queryAllTypeName() {
    return imgService.queryAllTypeName();
  }

  @RequestMapping(value = {"/img/delete"})
  @ResponseBody
  public RestResponse delete(@RequestParam("id") Integer id) {
    if (id == null) {
      return RestResponse.errorResponse("id is null");
    }
    return imgService.deleteById(id);
  }

  @RequestMapping(value = {"/img/add"})
  @ResponseBody
  public RestResponse add(@RequestBody ImgAddParam param) {
    param.checkParam();
    return imgService.add(ImgParamUtil.toSpec(param));
  }

  @RequestMapping(value = {"/img/update"})
  @ResponseBody
  public RestResponse update(@RequestBody ImgUpdateParam param) {
    param.checkParam();
    return imgService.update(ImgParamUtil.toSpec(param));
  }
}
