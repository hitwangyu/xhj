package com.xhj.spec;

import java.util.LinkedList;
import java.util.List;

public class ProductClassTree {
  private Integer index = 0; // 兄弟节点集中的序号
  private Integer id;
  private String name = "/";
  private Integer depth = 0; // 根节点
  private String path = "/";
  private String proListInOneClassUrl; // 品类下产品展示页面url

  private List<ProductClassTree> children = new LinkedList<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getDepth() {
    return depth;
  }

  public void setDepth(Integer depth) {
    this.depth = depth;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  /**
   * 深度遍历建树
   * 
   * @param node
   * @return 是否添加成功 created by wangyu on 2015年8月19日 下午11:28:30
   */
  public boolean addChild(ProductClassTree node) {
    if (node.getDepth() == 1) {
      node.setIndex(children.size());
      this.children.add(node);
      return true;
    }
    if (node.getPath().indexOf(this.path) == 0) {
      if (node.getDepth() == this.depth + 1) {
        node.setIndex(children.size());
        this.children.add(node);
        return true;
      }
    } else { // 必然不属于这个分支，不需要继续深度遍历了
      return false;
    }
    for (ProductClassTree treeNode : children) {
      boolean result = treeNode.addChild(node);
      if (result) {
        return true;
      }
    }
    return false;
  }

  public List<ProductClassTree> getChildren() {
    return children;
  }

  public Integer getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }

  public String getProListInOneClassUrl() {
    return proListInOneClassUrl;
  }

  public void setProListInOneClassUrl(String proListInOneClassUrl) {
    this.proListInOneClassUrl = proListInOneClassUrl;
  }

}
