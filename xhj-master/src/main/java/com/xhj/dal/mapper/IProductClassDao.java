package com.xhj.dal.mapper;

import java.util.List;

import com.xhj.annotation.MyBatisRepository;
import com.xhj.dal.base.BaseDao;
import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.query.ProductClassQuery;

@MyBatisRepository
public interface IProductClassDao extends BaseDao<ProductClassEntity> {

  public List<Integer> getIdListByQuery(ProductClassQuery query);

  public int getMaxDepth();

  public List<String> getAllProductClassPath();

}
