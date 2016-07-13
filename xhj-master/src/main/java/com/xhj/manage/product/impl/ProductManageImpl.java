package com.xhj.manage.product.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.xhj.dal.entity.ProductEntity;
import com.xhj.dal.mapper.IProductDao;
import com.xhj.manage.product.IProductManage;
import com.xhj.query.ProductQuery;

@Component
public class ProductManageImpl implements IProductManage {

  @Autowired
  private IProductDao productDao;

  @Override
  public ProductEntity getById(Integer id) {
    ProductQuery query = new ProductQuery();
    query.setId(id);
    List<ProductEntity> proList = productDao.query(query);
    return CollectionUtils.isEmpty(proList) ? null : proList.get(0);
  }

  @Override
  public List<ProductEntity> getListByQuery(ProductQuery query) {
    return productDao.query(query);
  }

  @Override
  public List<ProductEntity> getProductListGroupTopN(Set<Integer> classIdSet, Integer topN) {
    return productDao.getProductListGroupTopN(classIdSet, topN);
  }

  @Override
  public int getCountByQuery(ProductQuery query) {
    return productDao.queryCount(query);
  }

}
