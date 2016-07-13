package com.xhj.localCache;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

  private static ProductClassTree PRODUCT_CLASS_TREE = new ProductClassTree();


  @PostConstruct
  public void init() {
    refreshTree();
  }

  public static ProductClassTree getProductClassTree() {
    return PRODUCT_CLASS_TREE;
  }

  public void refreshTree() {
    PRODUCT_CLASS_TREE = new ProductClassTree();
    ProductClassQuery query = new ProductClassQuery();
    query.setIsQueryAll(Boolean.TRUE);
    query.setSortField(ProductClassColumn.DEPTH);
    query.setOrderBy(Boolean.TRUE); // 按深度升序排
    List<ProductClassEntity> classList = productClassDao.query(query);
    for (ProductClassEntity proClass : classList) {
      ProductClassTree treeNode = ProductClassUtil.toTreeNode(proClass);
      PRODUCT_CLASS_TREE.addChild(treeNode); // 必须按深度升序排列进行建树操作
    }
  }
}
