package com.xhj.localCache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.dal.entity.ImgEntity;
import com.xhj.dal.mapper.IImgDao;
import com.xhj.dal.tbColumn.ImgColumn;
import com.xhj.query.ImgQuery;

@Component
public class ImgCache {

  private static final Map<Integer, ImgEntity> IMG_ID_MAP = new HashMap<>();

  private static final Map<Integer, List<ImgEntity>> IMG_TYPE_MAP = new HashMap<>();

  @Autowired
  private IImgDao imgDao;

  @PostConstruct
  public void init() {
    refresh();
  }

  public void refresh() {
    IMG_TYPE_MAP.clear();
    IMG_ID_MAP.clear();
    ImgQuery query = new ImgQuery();
    query.setSortField(ImgColumn.WEIGHT);
    query.setIsQueryAll(Boolean.TRUE);
    query.setOrderBy(Boolean.FALSE);
    List<ImgEntity> imgList = imgDao.query(query);
    for (ImgEntity img : imgList) {
      IMG_ID_MAP.put(img.getId(), img);
      if (!IMG_TYPE_MAP.containsKey(img.getType())) {
        List<ImgEntity> temp = new LinkedList<>();
        IMG_TYPE_MAP.put(img.getType(), temp);
      }
      IMG_TYPE_MAP.get(img.getType()).add(img);
    }
  }

  public static ImgEntity getImg(Integer id) {
    return IMG_ID_MAP.get(id);
  }

  public static List<ImgEntity> getImgList(List<Integer> idList) {
    List<ImgEntity> imgList = new LinkedList<>();
    for (Integer id : idList) {
      imgList.add(IMG_ID_MAP.get(id));
    }
    return imgList;
  }

  public static Map<Integer, ImgEntity> getImgIdMap() {
    return IMG_ID_MAP;
  }

  public static List<ImgEntity> getImgListByType(ImgType type) {
    return IMG_TYPE_MAP.get(type.getType());
  }

}
