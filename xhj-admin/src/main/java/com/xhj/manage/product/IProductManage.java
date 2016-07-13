package com.xhj.manage.product;

import java.util.List;

import com.xhj.dal.entity.ProductEntity;
import com.xhj.query.ProductQuery;

public interface IProductManage {

  public ProductEntity getById(Integer id);

  public List<ProductEntity> getListByQuery(ProductQuery query);

  public void deleteById(Integer id);

  public void add(ProductEntity pro);

  public void update(ProductEntity pro);

  public int getCountByQuery(ProductQuery query);

}
