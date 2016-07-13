package com.xhj.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.controller.base.BaseController;
import com.xhj.localCache.ImgCache;
import com.xhj.localCache.IniCache;
import com.xhj.localCache.ProductClassCache;

@Controller
public class RefreshController extends BaseController {

  @Autowired
  private ProductClassCache productClassCache;
  @Autowired
  private IniCache iniCache;
  @Autowired
  private ImgCache imgCache;

  @RequestMapping(value = {"/refresh"})
  @ResponseBody
  public RestResponse update(@RequestParam("key") String key) {
    if (key.equalsIgnoreCase("proClass")) {
      productClassCache.refresh();
    } else if (key.equalsIgnoreCase("ini")) {
      iniCache.refresh();
    } else if (key.equalsIgnoreCase("img")) {
      imgCache.refresh();
    } else {
      return RestResponse.errorResponse("key不合法");
    }
    return RestResponse.successResponse(true);
  }
}
