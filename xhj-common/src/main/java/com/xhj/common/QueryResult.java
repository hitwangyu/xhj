package com.xhj.common;

import java.util.List;

public class QueryResult<P extends PageQuery, T extends Object> {
  private P queryCondition = null;
  private List<T> target = null;

  public P getQueryCondition() {
    return queryCondition;
  }

  public void setQueryCondition(P queryCondition) {
    this.queryCondition = queryCondition;
  }

  public List<T> getTarget() {
    return target;
  }

  public void setTarget(List<T> target) {
    this.target = target;
  }
}
