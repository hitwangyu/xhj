package com.xhj.dal.base;

import java.util.List;

public interface BaseDao<T> {
  public Long add(T object);

  public Integer update(T object);

  public List<T> query(Object object);

  public Integer queryCount(Object object);

  public Integer deleteById(Integer id);

}
