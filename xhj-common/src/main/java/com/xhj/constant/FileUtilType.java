package com.xhj.constant;

/**
 * 文件类型，用于区分上传文件的作用，如app图标/资质文件等
 * 
 */
public enum FileUtilType {
  Img(1);

  private Integer value;

  private FileUtilType(Integer level) {
    this.value = level;
  }

  public Integer getValue() {
    return this.value;
  }

  /**
   * from int to FileUtilType
   * 
   * @param level
   * @return
   */
  public static FileUtilType valueOf(int level) {
    switch (level) {
      case 1:
        return Img;
      default:
        return null;
    }
  }

  public static FileUtilType fromString(String str) {
    if (str.equalsIgnoreCase("img"))
      return Img;
    return null;
  }
};
