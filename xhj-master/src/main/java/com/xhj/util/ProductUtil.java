package com.xhj.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xhj.dal.entity.ImgEntity;
import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.dal.entity.ProductEntity;
import com.xhj.spec.ProductSpec;

public class ProductUtil {
  public static ProductSpec toSpec(ProductEntity pro, ImgEntity img, ProductClassEntity proClass) {
    ProductSpec spec = new ProductSpec();
    setCommonSpec(pro, proClass, spec);
    spec.setImgAddr(img.getImgAddr());
    spec.setLinkAddr(img.getLinkAddr());
    return spec;
  }

  private static void setCommonSpec(ProductEntity pro, ProductClassEntity proClass, ProductSpec spec) {
    spec.setId(pro.getId());
    spec.setDescription(pro.getDescription());
    spec.setName(pro.getName());
    spec.setIntroduction(pro.getIntroduction());
    spec.setPrice(pro.getPrice());
    spec.setClassName(proClass.getName());
    spec.setCreateTime(TimeUtil.yyyy_MM_dd.format(pro.getCreateTime()));
  }

  public static ProductSpec toSpec(ProductEntity pro, List<ImgEntity> imgList,
      ProductClassEntity proClass) {
    ProductSpec spec = new ProductSpec();
    setCommonSpec(pro, proClass, spec);
    List<String> detailImgAddrList = new ArrayList<>(imgList.size());
    for (ImgEntity img : imgList) {
      detailImgAddrList.add(img.getImgAddr());
    }
    spec.setDetailImgAddrList(detailImgAddrList);
    return spec;
  }

  /**
   * 商品列表
   * 
   * @param proList
   * @param imgMap
   * @param proClassMap
   * @return created by wangyu on 2015年8月23日 下午4:08:47
   */
  public static List<ProductSpec> toSpecList(List<ProductEntity> proList,
      Map<Integer, ImgEntity> imgMap, Map<Integer, ProductClassEntity> proClassMap) {
    List<ProductSpec> specList = new LinkedList<>();
    for (ProductEntity pro : proList) {
      specList.add(toSpec(pro, imgMap.get(pro.getFirstImgId()), proClassMap.get(pro.getClassId())));
    }

    return specList;
  }
}
