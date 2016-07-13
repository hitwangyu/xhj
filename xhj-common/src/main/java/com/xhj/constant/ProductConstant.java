package com.xhj.constant;

public class ProductConstant {

  public static enum ProStatus {
    /**
     * 0-未生效，1-生效
     */
    NO_EFFECTIVE(0), EFFECTIVE(1);
    private int code;

    private ProStatus(int code) {
      this.code = code;
    }

    public int getCode() {
      return code;
    }

    public static ProStatus getByCode(Integer code) {
      for (ProStatus status : ProStatus.values()) {
        if (status.getCode() == code) {
          return status;
        }
      }
      return null;
    }

  }
}
