package com.xhj.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.xhj.dal.entity.ImgEntity;
import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.dal.entity.ProductEntity;
import com.xhj.spec.ProductSpec;

public class ProductUtil {
  public static ProductSpec toSpec(ProductEntity pro, ImgEntity img, ProductClassEntity proClass) {
    ProductSpec spec = new ProductSpec();
    setCommonSpec(pro, proClass, spec);
    if (img != null) {
      spec.setImgAddr(img.getImgAddr());
      spec.setLinkAddr(img.getLinkAddr());
    }
    return spec;
  }

  private static void setCommonSpec(ProductEntity pro, ProductClassEntity proClass, ProductSpec spec) {
    spec.setId(pro.getId());
    spec.setStatus(pro.getStatus());
    spec.setIntroduction(pro.getIntroduction());
    spec.setDescription(pro.getDescription());
    spec.setName(pro.getName());
    spec.setPrice(pro.getPrice());
    spec.setImgIds(pro.getImgIds());
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

  public static List<ProductSpec> toSpecList(List<ProductEntity> proList,
      Map<Integer, ImgEntity> imgMap, Map<Integer, ProductClassEntity> proClassMap) {
    if (CollectionUtils.isEmpty(proList)) {
      return Collections.emptyList();
    }
    List<ProductSpec> specList = new LinkedList<>();
    for (ProductEntity pro : proList) {
      specList.add(toSpec(pro, imgMap.get(pro.getFirstImgId()), proClassMap.get(pro.getClassId())));
    }

    return specList;
  }

  public static ProductEntity toEntity(ProductSpec spec) {
    ProductEntity pro = new ProductEntity();
    pro.setClassId(spec.getClassId());
    pro.setIntroduction(spec.getIntroduction());
    pro.setDescription(spec.getDescription());
    pro.setImgIds(spec.getImgIds());
    pro.setName(spec.getName());
    pro.setPrice(spec.getPrice());
    return pro;
  }
}
