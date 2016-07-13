package com.xhj.service.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhj.common.QueryResult;
import com.xhj.common.RestResponse;
import com.xhj.dal.entity.ImgEntity;
import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.dal.entity.ProductEntity;
import com.xhj.localCache.ImgCache;
import com.xhj.localCache.ProductClassCache;
import com.xhj.manage.classification.IProductClassManage;
import com.xhj.manage.product.IProductManage;
import com.xhj.query.ProductQuery;
import com.xhj.service.product.IProductService;
import com.xhj.spec.ProductSpec;
import com.xhj.util.ProductClassUtil;
import com.xhj.util.ProductUtil;

@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private IProductManage productManage;
  @Autowired
  private IProductClassManage productClassManage;

  /**
   * 产品展示列表 { status:"success", data: [ { classification:"邮票", productList:
   * [{imgAddr:"xxx",name:"三轮生肖兔大版", price:"200~300"}, {} ] } { classification:"钱币", productList:
   * [{imgAddr:"xxx",name:"三轮生肖兔大版", price:"200~300"}, {} ] } ] }
   */
  @Override
  public RestResponse getProductListGroupTopN(Integer topN) {

    Set<Integer> classIdSet = ProductClassCache.getProductClassIdList();
    List<ProductEntity> proList = productManage.getProductListGroupTopN(classIdSet, topN);
    Map<String, List<ProductSpec>> map = new HashMap<>();
    for (ProductEntity pro : proList) {
      ProductClassEntity proClass = ProductClassCache.getProClassById(pro.getClassId());
      // 根据商品分类获取其所属的一级分类
      String firstLevelName = ProductClassUtil.getFirstLevelNameByPath(proClass.getPath());
      if (map.containsKey(firstLevelName) && map.get(firstLevelName).size() >= topN) { // 首页每个一级分类只展现topN个
        continue;
      }
      ImgEntity img = ImgCache.getImg(pro.getFirstImgId());
      ProductSpec spec = ProductUtil.toSpec(pro, img, proClass);
      if (map.containsKey(firstLevelName)) {
        map.get(firstLevelName).add(spec);
      } else {
        List<ProductSpec> specList = new LinkedList<ProductSpec>();
        specList.add(spec);
        map.put(firstLevelName, specList);
      }
    }
    List<Map<String, Object>> result = new ArrayList<>(classIdSet.size());
    for (Entry<String, List<ProductSpec>> entry : map.entrySet()) {
      Map<String, Object> element = new HashMap<>();
      element.put("classification", entry.getKey());
      element.put("productList", entry.getValue());
      result.add(element);
    }
    return RestResponse.successResponse(result);
  }

  @Override
  public RestResponse getProductDetailById(Integer id) {
    ProductEntity product = productManage.getById(id);
    if (product == null) {
      return RestResponse.errorResponse("商品不存在");
    }
    List<ImgEntity> detailImgList = new LinkedList<>();
    List<Integer> detailImgIdList = product.getImgIdList();
    for (Integer imgId : detailImgIdList) {
      detailImgList.add(ImgCache.getImg(imgId));
    }
    ProductClassEntity proClass = ProductClassCache.getProClassById(product.getClassId());
    return RestResponse.successResponse(ProductUtil.toSpec(product, detailImgList, proClass));
  }

  @Override
  public RestResponse getProductListByQuery(ProductQuery query) {
    if (StringUtils.isNotBlank(query.getClassName())) {
      ProductClassEntity proClass = ProductClassCache.getProClassByName(query.getClassName());
      query.setClassId(proClass.getId());
    } else if (StringUtils.isNotBlank(query.getAncestorClassName())) {
      ProductClassEntity proClass =
          ProductClassCache.getProClassByName(query.getAncestorClassName());
      List<Integer> classIdList = productClassManage.getLeafIdListByAncestorId(proClass.getId());
      query.setClassIdList(classIdList);
    }
    List<ProductEntity> proList = productManage.getListByQuery(query);
    List<ProductSpec> specList =
        ProductUtil.toSpecList(proList, ImgCache.getImgIdMap(),
            ProductClassCache.getProductClassIdMap());

    if (CollectionUtils.isNotEmpty(specList)) {
      query.setTotalSize(productManage.getCountByQuery(query));
      query.calTotalPage();
    } else {
      query.setTotalSize(0);
      query.setTotalPage(1);
    }
    QueryResult<ProductQuery, ProductSpec> result = new QueryResult<>();
    result.setQueryCondition(query);
    result.setTarget(specList);
    return RestResponse.successResponse(result);
  }
}
