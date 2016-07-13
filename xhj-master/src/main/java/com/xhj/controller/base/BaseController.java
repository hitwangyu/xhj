package com.xhj.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xhj.common.RestResponse;
import com.xhj.util.LogFactory;
import com.xhj.util.LogFactory.Log;

public class BaseController {
  private final Log logger = LogFactory.getLog(getClass());

  @ExceptionHandler(Exception.class)
  protected @ResponseBody RestResponse handleException(Exception ex, HttpServletRequest req) {
    RestResponse model = new RestResponse();
    model.setStatus(RestResponse.ERROR_STATUS);
    model.setErrorMessage("系统异常");
    logger.error(ex.getMessage(), ex);
    return model;
  }
}
