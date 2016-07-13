package com.xhj.dal.mapper;

import com.xhj.annotation.MyBatisRepository;
import com.xhj.dal.base.BaseDao;
import com.xhj.dal.entity.ProductEntity;

@MyBatisRepository
public interface IProductDao extends BaseDao<ProductEntity> {

}
