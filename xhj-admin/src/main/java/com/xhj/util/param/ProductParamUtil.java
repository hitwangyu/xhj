package com.xhj.util.param;

import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.manage.classification.IProductClassManage;
import com.xhj.param.product.ProductAddParam;
import com.xhj.param.product.ProductUpdateParam;
import com.xhj.spec.ProductSpec;
import com.xhj.spring.SpringContextHolder;

public class ProductParamUtil {
  public static ProductSpec toSpec(ProductAddParam param) {
    ProductSpec spec = new ProductSpec();
    spec.setName(param.getProductName());
    spec.setIntroduction(param.getIntroduction());
    spec.setDescription(param.getDescription());
    spec.setImgIds(param.getImgIds().replaceAll("，", ","));
    spec.setPrice(param.getPrice());
    IProductClassManage productClassManage = SpringContextHolder.getBean("productClassManageImpl");
    ProductClassEntity proClass = productClassManage.getByPath(param.getProductClassPath());
    spec.setClassId(proClass.getId());
    return spec;
  }

  public static ProductSpec toSpec(ProductUpdateParam param) {
    ProductSpec spec = new ProductSpec();
    spec.setId(param.getId());
    spec.setName(param.getProductName());
    spec.setIntroduction(param.getIntroduction());
    spec.setDescription(param.getDescription());
    spec.setImgIds(param.getImgIds().replaceAll("，", ","));
    spec.setPrice(param.getPrice());
    IProductClassManage productClassManage = SpringContextHolder.getBean("productClassManageImpl");
    ProductClassEntity proClass = productClassManage.getByPath(param.getProductClassPath());
    spec.setClassId(proClass.getId());
    return spec;
  }
}
