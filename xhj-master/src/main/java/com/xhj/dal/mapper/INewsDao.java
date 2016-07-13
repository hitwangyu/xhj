package com.xhj.dal.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.xhj.annotation.MyBatisRepository;
import com.xhj.dal.base.BaseDao;
import com.xhj.dal.entity.NewsEntity;

@MyBatisRepository
public interface INewsDao extends BaseDao<NewsEntity> {

  public List<NewsEntity> getNewsListGroupTopN(@Param("newsTypeSet") Set<Integer> newsTypeSet,
      @Param("topN") Integer topN);

}
