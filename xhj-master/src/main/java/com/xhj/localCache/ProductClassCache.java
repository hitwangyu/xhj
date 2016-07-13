package com.xhj.localCache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xhj.constant.UrlConstant;
import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.dal.mapper.IProductClassDao;
import com.xhj.dal.tbColumn.ProductClassColumn;
import com.xhj.query.ProductClassQuery;
import com.xhj.spec.ProductClassTree;
import com.xhj.util.ProductClassUtil;

@Component
public class ProductClassCache {
  @Autowired
  private IProductClassDao productClassDao;

  private static final Map<Integer, ProductClassEntity> PRODUCT_CLASS_ID_MAP = new HashMap<>();

  private static final Map<String, ProductClassEntity> PRODUCT_CLASS_NAME_MAP = new HashMap<>();

  private static final Set<Integer> PRODUCT_CLASS_ID = new HashSet<>();

  private static ProductClassTree PRODUCT_CLASS_TREE = new ProductClassTree();


  @PostConstruct
  public void init() {
    refresh();
  }

  public void refresh() {
    PRODUCT_CLASS_ID_MAP.clear();
    PRODUCT_CLASS_NAME_MAP.clear();
    PRODUCT_CLASS_ID.clear();
    PRODUCT_CLASS_TREE = new ProductClassTree();
    ProductClassQuery query = new ProductClassQuery();
    query.setIsQueryAll(Boolean.TRUE);
    query.setSortField(ProductClassColumn.DEPTH);
    query.setOrderBy(Boolean.TRUE); // 按深度升序排
    List<ProductClassEntity> classList = productClassDao.query(query);
    for (ProductClassEntity proClass : classList) {
      PRODUCT_CLASS_ID_MAP.put(proClass.getId(), proClass);
      PRODUCT_CLASS_NAME_MAP.put(proClass.getName(), proClass);
      PRODUCT_CLASS_ID.add(proClass.getId());
      ProductClassTree treeNode = ProductClassUtil.toTreeNode(proClass);
      treeNode.setProListInOneClassUrl(UrlConstant.PROLIST_IN_ONE_PRO_CLASS + "?className="
          + proClass.getName());
      PRODUCT_CLASS_TREE.addChild(treeNode); // 必须按深度升序排列进行建树操作
    }
  }

  public static Set<Integer> getProductClassIdList() {
    return PRODUCT_CLASS_ID;
  }

  public static ProductClassEntity getProClassById(Integer id) {
    return PRODUCT_CLASS_ID_MAP.get(id);
  }

  public static ProductClassEntity getProClassByName(String name) {
    return PRODUCT_CLASS_NAME_MAP.get(name);
  }

  public static Map<Integer, ProductClassEntity> getProductClassIdMap() {
    return PRODUCT_CLASS_ID_MAP;
  }

  public static ProductClassTree getProductClassTree() {
    return PRODUCT_CLASS_TREE;
  }

}
