package com.xhj.common;

public abstract class PageQuery {
  public Boolean getIsQueryAll() {
    return isQueryAll;
  }

  public void setIsQueryAll(Boolean isQueryAll) {
    this.isQueryAll = isQueryAll;
  }

  private Integer firstIndex;
  private Integer pageNo = 1;
  private Integer pageSize = 20;
  private String sortField;
  private Boolean orderBy = Boolean.FALSE; // false降序
  private Boolean isQueryAll = Boolean.FALSE; // 是否查询全部
  private Integer totalSize = 0;
  private Integer totalPage = 1;

  public Integer getFirstIndex() {
    if (firstIndex == null) {
      return (pageNo - 1) * pageSize;
    }
    return firstIndex;
  }

  public void setFirstIndex(Integer firstIndex) {
    this.firstIndex = firstIndex;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public String getSortField() {
    return sortField;
  }

  public void setSortField(String sortField) {
    this.sortField = sortField;
  }

  public Boolean getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Boolean orderBy) {
    this.orderBy = orderBy;
  }

  public void checkParam() {}

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public Integer getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(Integer totalSize) {
    this.totalSize = totalSize;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

  public void calTotalPage() {
    totalPage = (totalSize + pageSize - 1) / pageSize;
  }
}
