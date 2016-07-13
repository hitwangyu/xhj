package com.xhj.dal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xhj.annotation.MyBatisRepository;
import com.xhj.dal.base.BaseDao;
import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.query.ProductClassQuery;

@MyBatisRepository
public interface IProductClassDao extends BaseDao<ProductClassEntity> {

  public List<Integer> getIdListByQuery(ProductClassQuery query);

  public int getMaxDepth();

  public List<String> getProductClassPathByQuery(ProductClassQuery query);

  public void deleteByPath(@Param("path") String path);

}
