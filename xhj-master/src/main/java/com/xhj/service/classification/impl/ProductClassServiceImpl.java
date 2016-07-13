package com.xhj.service.classification.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhj.common.RestResponse;
import com.xhj.localCache.ProductClassCache;
import com.xhj.manage.classification.IProductClassManage;
import com.xhj.service.classification.IProductClassService;

@Service
public class ProductClassServiceImpl implements IProductClassService {

  @Autowired
  private IProductClassManage productClassManage;

  @Override
  public RestResponse getAllProductClassPath() {
    List<String> allProductClassPath = productClassManage.getAllProductClassPath();
    Iterator<String> iterator = allProductClassPath.iterator();
    while (iterator.hasNext()) {
      String path = iterator.next();
      if (path.lastIndexOf("/") > 0) {
        iterator.remove();
      }
    }
    return RestResponse.successResponse(allProductClassPath);
  }

  @Override
  public RestResponse getProductClassTree() {
    return RestResponse.successResponse(ProductClassCache.getProductClassTree());
  }
}
