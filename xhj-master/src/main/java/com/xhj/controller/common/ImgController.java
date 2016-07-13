package com.xhj.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.controller.base.BaseController;
import com.xhj.localCache.ImgCache;
import com.xhj.util.ImgUtil;

@Controller
public class ImgController extends BaseController {

  @RequestMapping(value = {"/getBannerImg"})
  @ResponseBody
  public RestResponse getBannerImg() {
    return RestResponse.successResponse(ImgUtil.toSpecList(ImgCache
        .getImgListByType(ImgType.BANNER)));
  }

}
