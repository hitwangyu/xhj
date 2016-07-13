package com.xhj.dal.entity;

import java.util.Date;

public class IniEntity {
  private String iniKey;
  private String iniValue;
  private Date expireTime;


  public Date getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Date expireTime) {
    this.expireTime = expireTime;
  }

  public String getIniKey() {
    return iniKey;
  }

  public void setIniKey(String iniKey) {
    this.iniKey = iniKey;
  }

  public String getIniValue() {
    return iniValue;
  }

  public void setIniValue(String iniValue) {
    this.iniValue = iniValue;
  }

}
