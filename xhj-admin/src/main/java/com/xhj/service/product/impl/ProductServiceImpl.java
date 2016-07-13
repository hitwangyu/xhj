package com.xhj.service.product.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhj.common.QueryResult;
import com.xhj.common.RestResponse;
import com.xhj.dal.entity.ImgEntity;
import com.xhj.dal.entity.ProductEntity;
import com.xhj.manage.classification.IProductClassManage;
import com.xhj.manage.img.IImgManage;
import com.xhj.manage.product.IProductManage;
import com.xhj.query.ProductQuery;
import com.xhj.service.product.IProductService;
import com.xhj.spec.ProductSpec;
import com.xhj.util.ProductUtil;

@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private IProductManage productManage;

  @Autowired
  private IImgManage imgManage;

  @Autowired
  private IProductClassManage productClassManage;

  @Override
  public RestResponse getListByQuery(ProductQuery query) {
    List<Integer> classIdList = null;
    if (StringUtils.isNotBlank(query.getPrePath())) {
      classIdList = productClassManage.getDescendantIdListByPath(query.getPrePath());
      if (CollectionUtils.isEmpty(classIdList)) {
        return RestResponse.successResponse(Collections.emptyList()); // 如果选择了分类但是没有叶子分类，直接返回空集
      }
    }
    if (CollectionUtils.isNotEmpty(classIdList)) {
      query.setClassIdList(classIdList);
    }
    query.setIsQueryAll(Boolean.TRUE);
    List<ProductEntity> proList = productManage.getListByQuery(query);
    List<Integer> imgIdList = new LinkedList<>();
    Set<Integer> proClassIdSet = new HashSet<>();
    for (ProductEntity pro : proList) {
      imgIdList.add(pro.getFirstImgId());
      proClassIdSet.add(pro.getClassId());
    }
    List<ProductSpec> specList =
        ProductUtil.toSpecList(proList, imgManage.getImgIdMapByIdList(imgIdList),
            productClassManage.getIdMapByIdList(new ArrayList<>(proClassIdSet)));

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

  @Override
  public RestResponse deleteById(Integer id) {
    productManage.deleteById(id);
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse add(ProductSpec spec) {
    spec.setImgIds(imgManage.sortByWeight(spec.getImgIds()));
    ProductEntity pro = ProductUtil.toEntity(spec);
    productManage.add(pro);
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse getById(Integer id) {
    ProductEntity pro = productManage.getById(id);
    return RestResponse.successResponse(ProductUtil.toSpec(pro, new ImgEntity(),
        productClassManage.getById(pro.getClassId())));
  }

  @Override
  public RestResponse update(ProductSpec spec) {
    ProductEntity pro = productManage.getById(spec.getId());
    if (pro == null) {
      return RestResponse.errorResponse("商品不存在");
    }
    updateEntity(pro, spec);
    productManage.update(pro);
    return RestResponse.successResponse(true);
  }

  private void updateEntity(ProductEntity pro, ProductSpec spec) {
    pro.setId(spec.getId());
    if (spec.getStatus() != null) {
      pro.setStatus(spec.getStatus());
    }
    if (StringUtils.isNotBlank(spec.getName())) {
      pro.setName(spec.getName());
    }
    if (StringUtils.isNotBlank(spec.getIntroduction())) {
      pro.setIntroduction(spec.getIntroduction());
    }
    if (StringUtils.isNotBlank(spec.getDescription())) {
      pro.setDescription(spec.getDescription());
    }
    if (StringUtils.isNotBlank(spec.getPrice())) {
      pro.setPrice(spec.getPrice());
    }
    if (StringUtils.isNotBlank(spec.getImgIds())) {
      pro.setImgIds(spec.getImgIds());
    }
    if (spec.getClassId() != null) {
      pro.setClassId(spec.getClassId());
    }
  }

}
