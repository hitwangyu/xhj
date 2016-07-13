package com.xhj.manage.img.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xhj.constant.CommonConstant;
import com.xhj.dal.entity.ImgEntity;
import com.xhj.dal.mapper.IImgDao;
import com.xhj.dal.tbColumn.ImgColumn;
import com.xhj.manage.img.IImgManage;
import com.xhj.query.ImgQuery;

@Component
public class ImgManageImpl implements IImgManage {
  @Autowired
  private IImgDao imgDao;

  public static final Comparator<ImgEntity> IMG_COMPARATOR_DESC = new Comparator<ImgEntity>() {

    @Override
    public int compare(ImgEntity o1, ImgEntity o2) {
      return o1.getWeight() >= o2.getWeight() ? -1 : 1;
    }

  };

  @Override
  public List<ImgEntity> getListByQuery(ImgQuery query) {
    return imgDao.query(query);
  }

  @Override
  public Map<Integer, ImgEntity> getImgIdMap() {
    Map<Integer, ImgEntity> imgIdMap = new HashMap<>();
    ImgQuery query = new ImgQuery();
    query.setSortField(ImgColumn.WEIGHT);
    query.setIsQueryAll(Boolean.TRUE);
    query.setOrderBy(Boolean.FALSE);
    List<ImgEntity> imgList = imgDao.query(query);
    for (ImgEntity img : imgList) {
      imgIdMap.put(img.getId(), img);
    }
    return imgIdMap;
  }

  @Override
  public Map<Integer, ImgEntity> getImgIdMapByIdList(List<Integer> idList) {
    Map<Integer, ImgEntity> imgIdMap = new HashMap<>();
    if (CollectionUtils.isEmpty(idList)) {
      return imgIdMap;
    }
    ImgQuery query = new ImgQuery();
    query.setSortField(ImgColumn.WEIGHT);
    query.setIsQueryAll(Boolean.TRUE);
    query.setOrderBy(Boolean.FALSE);
    query.setIdList(idList);
    List<ImgEntity> imgList = imgDao.query(query);
    for (ImgEntity img : imgList) {
      imgIdMap.put(img.getId(), img);
    }
    return imgIdMap;
  }

  @Override
  public Map<Integer, List<ImgEntity>> getImgTypeMap() {
    ImgQuery query = new ImgQuery();
    query.setSortField(ImgColumn.WEIGHT);
    query.setIsQueryAll(Boolean.TRUE);
    query.setOrderBy(Boolean.FALSE);
    List<ImgEntity> imgList = imgDao.query(query);
    Map<Integer, List<ImgEntity>> imgTypeMap = new HashMap<>();
    for (ImgEntity img : imgList) {
      if (!imgTypeMap.containsKey(img.getType())) {
        List<ImgEntity> temp = new LinkedList<>();
        imgTypeMap.put(img.getType(), temp);
      }
      imgTypeMap.get(img.getType()).add(img);
    }
    return imgTypeMap;
  }

  @Override
  public ImgEntity getById(Integer id) {
    ImgQuery query = new ImgQuery();
    query.setId(id);
    List<ImgEntity> imgList = imgDao.query(query);
    return CollectionUtils.isEmpty(imgList) ? null : imgList.get(0);
  }

  @Override
  public void deleteById(Integer id) {
    imgDao.deleteById(id);
  }

  @Override
  public void add(ImgEntity img) {
    imgDao.add(img);
  }

  @Override
  public ImgEntity getImgWithMaxWeight(List<Integer> imgIdList) {
    ImgQuery query = new ImgQuery();
    query.setIdList(imgIdList);
    query.setIsQueryAll(Boolean.TRUE);
    List<ImgEntity> imgList = imgDao.query(imgIdList);
    if (CollectionUtils.isEmpty(imgList)) {
      return null;
    }
    Collections.sort(imgList, IMG_COMPARATOR_DESC);
    return imgList.get(0);
  }

  @Override
  public List<Integer> sortByWeight(List<Integer> imgIdList) {
    ImgQuery query = new ImgQuery();
    query.setIdList(imgIdList);
    query.setIsQueryAll(Boolean.TRUE);
    List<ImgEntity> imgList = imgDao.query(query);
    if (CollectionUtils.isEmpty(imgList)) {
      return null;
    }
    Collections.sort(imgList, IMG_COMPARATOR_DESC);
    List<Integer> result = new LinkedList<>();
    for (ImgEntity img : imgList) {
      result.add(img.getId());
    }
    return result;
  }

  @Override
  public String sortByWeight(String ids) {
    String[] idStr = ids.split(CommonConstant.PRO_IMG_IDS_SEPARATOR);
    List<Integer> idList = new LinkedList<Integer>();
    for (String id : idStr) {
      idList.add(Integer.valueOf(id));
    }
    idList = this.sortByWeight(idList);
    return StringUtils.join(idList, CommonConstant.PRO_IMG_IDS_SEPARATOR);
  }

  @Override
  public void update(ImgEntity img) {
    imgDao.update(img);
  }

  @Override
  public int getCountByQuery(ImgQuery query) {
    return imgDao.queryCount(query);
  }
}
