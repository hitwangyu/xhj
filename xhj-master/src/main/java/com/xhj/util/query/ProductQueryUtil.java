package com.xhj.util.query;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.ProductConstant.ProStatus;
import com.xhj.param.product.ProductQueryParam;
import com.xhj.query.ProductQuery;


public class ProductQueryUtil {
  public static ProductQuery toQuery(ProductQueryParam param) {
    ProductQuery query = new ProductQuery();
    query.setStatus(ProStatus.EFFECTIVE.getCode());
    if (StringUtils.isNotBlank(param.getClassName()))
      query.setAncestorClassName(param.getClassName()); // 设置祖先类
    if (param.getPageNo() == null || param.getPageSize() == null) {
      query.setIsQueryAll(Boolean.TRUE);
    } else {
      query.setPageSize(param.getPageSize());
      query.setPageNo(param.getPageNo());
    }
    return query;
  }
}
