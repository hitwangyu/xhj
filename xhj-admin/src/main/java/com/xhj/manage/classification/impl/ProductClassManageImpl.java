package com.xhj.manage.classification.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.dal.mapper.IProductClassDao;
import com.xhj.localCache.ProductClassCache;
import com.xhj.manage.classification.IProductClassManage;
import com.xhj.query.ProductClassQuery;
import com.xhj.spec.ProductClassTree;

@Component
public class ProductClassManageImpl implements IProductClassManage {
  @Autowired
  private IProductClassDao productClassDao;

  @Override
  public ProductClassEntity getById(Integer id) {
    ProductClassQuery query = new ProductClassQuery();
    query.setId(id);
    List<ProductClassEntity> proList = productClassDao.query(query);
    return CollectionUtils.isEmpty(proList) ? null : proList.get(0);
  }

  @Override
  public List<ProductClassEntity> getProductClassListByQuery(ProductClassQuery query) {
    return productClassDao.query(query);
  }

  @Override
  public Map<Integer, ProductClassEntity> getIdMapByIdList(List<Integer> idList) {
    Map<Integer, ProductClassEntity> idMap = new HashMap<>();
    if (CollectionUtils.isEmpty(idList)) {
      return idMap;
    }
    ProductClassQuery query = new ProductClassQuery();
    query.setIdList(idList);
    query.setIsQueryAll(Boolean.TRUE);
    List<ProductClassEntity> classList = productClassDao.query(query);
    for (ProductClassEntity proClass : classList) {
      idMap.put(proClass.getId(), proClass);
    }
    return idMap;
  }

  @Override
  public List<Integer> getIdListByQuery(ProductClassQuery query) {
    return productClassDao.getIdListByQuery(query);
  }

  @Override
  public List<Integer> getDescendantIdListByAncestorId(Integer classId) {
    ProductClassQuery query = new ProductClassQuery();
    query.setId(classId);
    List<ProductClassEntity> classList = productClassDao.query(query);
    if (CollectionUtils.isEmpty(classList)) {
      return Collections.emptyList();
    }
    return getDescendantIdList(classList.get(0));
  }

  @Override
  public List<Integer> getDescendantIdListByPath(String path) {
    ProductClassQuery query = new ProductClassQuery();
    query.setPath(path);
    List<ProductClassEntity> classList = productClassDao.query(query);
    if (CollectionUtils.isEmpty(classList)) {
      return Collections.emptyList();
    }
    return getDescendantIdList(classList.get(0));
  }

  private List<Integer> getDescendantIdList(ProductClassEntity proClass) {
    ProductClassTree tree = ProductClassCache.getProductClassTree();
    ProductClassTree parentNode = findNode(tree, proClass.getPath());
    return getAllDescendantIds(parentNode);
  }

  /**
   * 获取所有后代分类id
   * 
   * @param parentNode
   * @return created by wangyu on 2015年9月8日 下午8:02:33
   */
  private List<Integer> getAllDescendantIds(ProductClassTree parentNode) {
    if (parentNode == null) {
      return Collections.emptyList();
    }
    List<Integer> result = new LinkedList<>();
    result.add(parentNode.getId());
    if (CollectionUtils.isEmpty(parentNode.getChildren())) // 自己就是叶子节点
    {
      return result;
    }
    Queue<ProductClassTree> scanQueue = new LinkedList<>();
    scanQueue.addAll(parentNode.getChildren());
    ProductClassTree curNode;
    while (CollectionUtils.isNotEmpty(scanQueue)) {
      curNode = scanQueue.poll();
      result.add(curNode.getId());
      if (CollectionUtils.isNotEmpty(curNode.getChildren())) {
        scanQueue.addAll(curNode.getChildren());
      }
    }
    return result;
  }

  private ProductClassTree findNode(ProductClassTree root, String path) {
    Queue<ProductClassTree> scanQueue = new LinkedList<>();
    scanQueue.addAll(root.getChildren());
    ProductClassTree curNode;
    while (CollectionUtils.isNotEmpty(scanQueue)) {
      curNode = scanQueue.poll();
      if (curNode.getPath().equals(path)) {
        return curNode;
      } else if (path.indexOf(curNode.getPath()) == 0) {
        scanQueue.clear();
        scanQueue.addAll(curNode.getChildren());
      }
    }
    return null;
  }

  @Override
  public ProductClassEntity getByPath(String path) {
    ProductClassQuery query = new ProductClassQuery();
    query.setPath(path);
    List<ProductClassEntity> classList = productClassDao.query(query);
    return CollectionUtils.isEmpty(classList) ? null : classList.get(0);
  }

  @Override
  public List<String> getProductClassPathByQuery(ProductClassQuery query) {
    return productClassDao.getProductClassPathByQuery(query);
  }

  @Override
  public void add(ProductClassEntity entity) {
    productClassDao.add(entity);
  }

  @Override
  public void deleteByPath(String path) {
    productClassDao.deleteByPath(path);
  }

  @Override
  public void update(ProductClassEntity entity) {
    productClassDao.update(entity);
  }
}
