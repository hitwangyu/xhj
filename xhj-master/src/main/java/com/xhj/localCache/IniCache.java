package com.xhj.localCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xhj.dal.entity.IniEntity;
import com.xhj.dal.mapper.IIniDao;
import com.xhj.exception.BusinessException;
import com.xhj.query.IniQuery;

@Component
public class IniCache {

  @Autowired
  private IIniDao iniDao;

  private static Map<String, IniEntity> iniMap = new HashMap<String, IniEntity>();

  @PostConstruct
  public void init() {
    refresh();
  }

  public void refresh() {
    List<IniEntity> iniList = iniDao.query(new IniQuery());
    for (IniEntity ini : iniList) {
      iniMap.put(ini.getIniKey(), ini);
    }
  }

  /**
   * 根据key获得ini的值
   * 
   * @param key
   * @return
   */
  public static <T> T getIniValue(String key) {
    if (!iniMap.containsKey(key)) {
      throw new BusinessException("no ini found for :" + key);
    }
    return (T) iniMap.get(key).getIniValue();
  }

  /**
   * 根据key获得ini值，可以指定默认值
   * 
   * @param key
   * @param defaultValue
   * @return
   */
  public static <T> T getIniValue(String key, T defaultValue) {
    if (iniMap.containsKey(key)) {
      return (T) iniMap.get(key).getIniValue();
    } else if (defaultValue != null) {
      return defaultValue;
    } else {
      throw new BusinessException("no ini found for :" + key);
    }
  }
}
