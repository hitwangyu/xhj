package com.xhj.manage.classification;

import java.util.List;
import java.util.Map;

import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.query.ProductClassQuery;

public interface IProductClassManage {

  public ProductClassEntity getByPath(String path);

  /**
   * id映射
   * 
   * @param idList
   * @return created by wangyu on 2015年8月9日 下午6:41:50
   */
  public Map<Integer, ProductClassEntity> getIdMapByIdList(List<Integer> idList);

  public List<Integer> getIdListByQuery(ProductClassQuery query);

  /**
   * 根据祖先节点获取叶子分类
   * 
   * @param path
   * @return created by wangyu on 2015年8月9日 下午9:34:59
   */
  public List<Integer> getLeafIdListByPath(String path);

  /**
   * 根据祖先节点获取叶子分类
   * 
   * @param classId
   * @return created by wangyu on 2015年8月9日 下午9:34:59
   */
  List<Integer> getLeafIdListByAncestorId(Integer classId);

  public List<String> getAllProductClassPath();

  public List<ProductClassEntity> getAllProductClass();

}
