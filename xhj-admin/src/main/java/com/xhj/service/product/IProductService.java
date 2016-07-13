package com.xhj.service.product;

import com.xhj.common.RestResponse;
import com.xhj.query.ProductQuery;
import com.xhj.spec.ProductSpec;

public interface IProductService {

  public RestResponse getListByQuery(ProductQuery query);

  public RestResponse deleteById(Integer id);

  public RestResponse add(ProductSpec spec);

  public RestResponse getById(Integer id);

  public RestResponse update(ProductSpec spec);

}
