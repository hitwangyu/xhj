package com.xhj.util.query;

import org.apache.commons.lang3.StringUtils;

import com.xhj.param.product.ProductQueryParam;
import com.xhj.query.ProductQuery;


public class ProductQueryUtil {
  public static ProductQuery toQuery(ProductQueryParam param) {
    ProductQuery query = new ProductQuery();
    if (StringUtils.isNotBlank(param.getProductName()))
      query.setLikeName(param.getProductName());
    if (StringUtils.isNotBlank(param.getProductClassPath()))
      query.setPrePath(param.getProductClassPath());
    if (param.getPageNo() == null || param.getPageSize() == null) {
      query.setIsQueryAll(Boolean.TRUE);
    } else {
      query.setPageSize(param.getPageSize());
      query.setPageNo(param.getPageNo());
    }
    return query;
  }
}
