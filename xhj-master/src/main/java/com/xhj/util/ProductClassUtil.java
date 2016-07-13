package com.xhj.util;

import org.apache.commons.lang3.StringUtils;

import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.spec.ProductClassSpec;
import com.xhj.spec.ProductClassTree;

public class ProductClassUtil {
  public static String getFirstLevelNameByPath(String path) {
    if (StringUtils.isBlank(path)) {
      return "";
    }
    String name = path.substring(1);
    if (name.indexOf("/") < 0) {
      return name;
    }
    return name.substring(0, name.indexOf("/"));
  }

  public static ProductClassSpec toSpec(ProductClassEntity proClass) {
    ProductClassSpec spec = new ProductClassSpec();
    spec.setId(proClass.getId());
    spec.setDepth(proClass.getDepth());
    spec.setName(proClass.getName());
    spec.setPath(proClass.getPath());
    return spec;
  }

  public static ProductClassTree toTreeNode(ProductClassEntity proClass) {
    ProductClassTree node = new ProductClassTree();
    node.setId(proClass.getId());
    node.setDepth(proClass.getDepth());
    node.setName(proClass.getName());
    node.setPath(proClass.getPath());
    return node;
  }
}
