package com.xhj.dal.mapper;

import com.xhj.annotation.MyBatisRepository;
import com.xhj.dal.base.BaseDao;
import com.xhj.dal.entity.NewsEntity;

@MyBatisRepository
public interface INewsDao extends BaseDao<NewsEntity> {

}
