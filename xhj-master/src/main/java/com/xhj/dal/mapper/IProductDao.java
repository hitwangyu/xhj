package com.xhj.dal.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.xhj.annotation.MyBatisRepository;
import com.xhj.dal.base.BaseDao;
import com.xhj.dal.entity.ProductEntity;

@MyBatisRepository
public interface IProductDao extends BaseDao<ProductEntity> {

  List<ProductEntity> getProductListGroupTopN(@Param("classIdSet") Set<Integer> classIdSet,
      @Param("topN") Integer topN);

}
