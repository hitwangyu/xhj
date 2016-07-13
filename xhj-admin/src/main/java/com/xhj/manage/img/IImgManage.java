package com.xhj.manage.img;

import java.util.List;
import java.util.Map;

import com.xhj.dal.entity.ImgEntity;
import com.xhj.query.ImgQuery;

public interface IImgManage {

  public List<ImgEntity> getListByQuery(ImgQuery query);

  /**
   * id img映射
   * 
   * @return created by wangyu on 2015年8月9日 下午6:24:03
   */
  public Map<Integer, ImgEntity> getImgIdMap();

  /**
   * type img映射
   * 
   * @return created by wangyu on 2015年8月9日 下午6:24:03
   */
  public Map<Integer, List<ImgEntity>> getImgTypeMap();

  public Map<Integer, ImgEntity> getImgIdMapByIdList(List<Integer> idList);

  public ImgEntity getById(Integer id);

  public void deleteById(Integer id);

  public void add(ImgEntity img);

  /**
   * 获取权值最高的图片
   * 
   * @param imgIdList
   * @return created by wangyu on 2015年8月23日 下午4:01:24
   */
  public ImgEntity getImgWithMaxWeight(List<Integer> imgIdList);

  /**
   * 根据权值降序排序
   * 
   * @param imgIdList
   * @return created by wangyu on 2015年8月23日 下午4:19:38
   */
  public List<Integer> sortByWeight(List<Integer> imgIdList);

  public String sortByWeight(String ids);

  public void update(ImgEntity img);

  public int getCountByQuery(ImgQuery query);
}
