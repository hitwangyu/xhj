package com.xhj.util.param;

import org.apache.commons.lang3.StringUtils;

import com.xhj.param.productClass.ProductClassAddParam;
import com.xhj.param.productClass.ProductClassUpdateParam;
import com.xhj.spec.ProductClassSpec;


public class ProductClassParamUtil {
  public static ProductClassSpec toSpec(ProductClassAddParam param) {
    ProductClassSpec spec = new ProductClassSpec();
    spec.setName(param.getName());
    String path = "/" + param.getName();
    if (StringUtils.isNotBlank(param.getParentPath())) {
      path = param.getParentPath() + path;
    }
    spec.setPath(path);
    return spec;
  }

  public static ProductClassSpec toSpec(ProductClassUpdateParam param) {
    ProductClassSpec spec = new ProductClassSpec();
    spec.setName(param.getName());
    spec.setPath(param.getPath());
    return spec;
  }

}
