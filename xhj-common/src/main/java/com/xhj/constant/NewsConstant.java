package com.xhj.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * 新闻常量
 * 
 * @author Administrator
 *
 */
public class NewsConstant {

  public static enum NewsType {
    NJWJS(1, "南京文交所"), ZNWJS(2, "中南文交所"), HYXW(3, "行业新闻"), TZFX(4, "投资分析");

    private int type;
    private String name;

    private NewsType(int type, String name) {
      this.type = type;
      this.name = name;
    }

    public int getType() {
      return type;
    }

    public String getName() {
      return name;
    }

    public static Set<Integer> getAllType() {
      Set<Integer> newsTypeSet = new HashSet<>();
      for (NewsType type : NewsType.values()) {
        newsTypeSet.add(type.getType());
      }
      return newsTypeSet;
    }

    public static Set<String> getAllTypeName() {
      Set<String> newsTypeSet = new HashSet<>();
      for (NewsType type : NewsType.values()) {
        newsTypeSet.add(type.getName());
      }
      return newsTypeSet;
    }

    public static NewsType getByName(String name) {
      for (NewsType type : NewsType.values()) {
        if (type.getName().equals(name)) {
          return type;
        }
      }
      return null;
    }

    public static NewsType getByCode(Integer code) {
      for (NewsType type : NewsType.values()) {
        if (type.getType() == code) {
          return type;
        }
      }
      return null;
    }


  }

  public static enum NewsStatus {
    /**
     * 0-未生效，1-生效
     */
    NO_EFFECTIVE(0), EFFECTIVE(1);
    private int code;

    private NewsStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public static NewsStatus getByCode(Integer code) {
      for (NewsStatus status : NewsStatus.values()) {
        if (status.getCode() == code) {
          return status;
        }
      }
      return null;
    }

  }
}
