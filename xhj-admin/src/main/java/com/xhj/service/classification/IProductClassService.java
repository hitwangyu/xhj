package com.xhj.service.classification;

import com.xhj.common.RestResponse;
import com.xhj.spec.ProductClassSpec;

public interface IProductClassService {
  public RestResponse getAllProductClassPathByLevel(Integer level);

  public RestResponse getProductClassTree();

  public RestResponse add(ProductClassSpec spec);

  public RestResponse deleteByPath(String path);

  public RestResponse update(ProductClassSpec spec);
}
