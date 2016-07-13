package com.xhj.service.classification;

import com.xhj.common.RestResponse;

public interface IProductClassService {
  public RestResponse getAllProductClassPath();

  /**
   * 导航栏，商品分类树
   * 
   * @return created by wangyu on 2015年8月19日 下午9:59:09
   */
  public RestResponse getProductClassTree();
}
