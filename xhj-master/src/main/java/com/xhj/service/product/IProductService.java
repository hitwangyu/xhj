package com.xhj.service.product;

import com.xhj.common.RestResponse;
import com.xhj.query.ProductQuery;

public interface IProductService {

  public RestResponse getProductListGroupTopN(Integer topN);

  public RestResponse getProductDetailById(Integer id);


  public RestResponse getProductListByQuery(ProductQuery query);

}
