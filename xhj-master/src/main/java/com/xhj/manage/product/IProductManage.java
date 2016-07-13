package com.xhj.manage.product;

import java.util.List;
import java.util.Set;

import com.xhj.dal.entity.ProductEntity;
import com.xhj.query.ProductQuery;

public interface IProductManage {

  public ProductEntity getById(Integer id);

  public List<ProductEntity> getListByQuery(ProductQuery query);

  public List<ProductEntity> getProductListGroupTopN(Set<Integer> classIdSet, Integer topN);

  public int getCountByQuery(ProductQuery query);

}
