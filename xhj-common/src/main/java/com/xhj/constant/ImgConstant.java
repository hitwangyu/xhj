package com.xhj.constant;

import java.util.LinkedList;
import java.util.List;

/**
 * 图片常量
 * 
 * @author Administrator
 *
 */
public class ImgConstant {

  /**
   * 1-轮播图,2-首页新闻列表_南京文交所图片,3-首页新闻列表_中南文交所图片,4-产品详情图片
   * 
   * @author Administrator
   *
   */
  public static enum ImgType {
    BANNER(1, "轮播图"), INDEX_XW_NJWJS(2, "首页新闻列表_南京文交所图片"), INDEX_XW_ZNWJS(3, "首页新闻列表_中南文交所图片"), PRODUCT_DETAIL(
        4, "产品详情图片");

    private int type;
    private String name;

    private ImgType(int type, String name) {
      this.type = type;
      this.name = name;
    }

    public int getType() {
      return type;
    }

    public static ImgType getByCode(int argType) {
      for (ImgType one : ImgType.values()) {
        if (one.getType() == argType) {
          return one;
        }
      }
      return null;
    }

    public static ImgType getByName(String name) {
      for (ImgType one : ImgType.values()) {
        if (one.getName().equals(name)) {
          return one;
        }
      }
      return null;
    }

    public static List<String> getAllTypeName() {
      List<String> nameList = new LinkedList<>();
      for (ImgType one : ImgType.values()) {
        nameList.add(one.getName());
      }
      return nameList;
    }

    public String getName() {
      return name;
    }

  }

}
