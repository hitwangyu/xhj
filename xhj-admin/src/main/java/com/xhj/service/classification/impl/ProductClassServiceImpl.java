package com.xhj.service.classification.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhj.common.RestResponse;
import com.xhj.constant.CommonConstant;
import com.xhj.dal.entity.ProductClassEntity;
import com.xhj.localCache.ProductClassCache;
import com.xhj.manage.classification.IProductClassManage;
import com.xhj.query.ProductClassQuery;
import com.xhj.service.classification.IProductClassService;
import com.xhj.spec.ProductClassSpec;
import com.xhj.util.ProductClassUtil;

@Service
public class ProductClassServiceImpl implements IProductClassService {

  @Autowired
  private IProductClassManage productClassManage;
  @Autowired
  private ProductClassCache productClassCache;

  @Override
  public RestResponse getAllProductClassPathByLevel(Integer level) {
    ProductClassQuery query = new ProductClassQuery();
    query.setDepth(level);
    query.setIsQueryAll(Boolean.TRUE);
    List<String> allProductClassPath = productClassManage.getProductClassPathByQuery(query);
    return RestResponse.successResponse(allProductClassPath);
  }

  @Override
  public RestResponse getProductClassTree() {
    return RestResponse.successResponse(ProductClassCache.getProductClassTree());
  }

  @Override
  public RestResponse add(ProductClassSpec spec) {
    String parentPath = spec.getPath();
    parentPath =
        parentPath.substring(0, parentPath.lastIndexOf(CommonConstant.PRO_CLASS_PATH_SEPARATOR));

    if (StringUtils.isNotBlank(parentPath)) { // 非一级品类
      ProductClassQuery query = new ProductClassQuery();
      query.setPath(parentPath);
      List<ProductClassEntity> classList = productClassManage.getProductClassListByQuery(query);
      if (CollectionUtils.isEmpty(classList)) {
        return RestResponse.errorResponse("上级品类不存在！");
      }
      ProductClassEntity parent = classList.get(0);
      spec.setDepth(parent.getDepth() + 1);
      spec.setParentId(parent.getId());
    } else {
      spec.setDepth(1); // 一级品类
      spec.setParentId(0);
    }
    productClassManage.add(ProductClassUtil.toEntity(spec));
    productClassCache.refreshTree();
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse deleteByPath(String path) {
    productClassManage.deleteByPath(path);
    productClassCache.refreshTree();
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse update(ProductClassSpec spec) {
    ProductClassQuery query = new ProductClassQuery();
    query.setPath(spec.getPath());
    List<ProductClassEntity> classList = productClassManage.getProductClassListByQuery(query);
    if (CollectionUtils.isEmpty(classList)) {
      return RestResponse.errorResponse("品类不存在！");
    }
    ProductClassEntity origin = classList.get(0);
    updateEntity(origin, spec);
    productClassManage.update(origin);
    productClassCache.refreshTree();
    return RestResponse.successResponse(true);
  }

  /**
   * 更新字段
   * 
   * @param origin
   * @param spec created by wangyu on 2015年8月22日 下午2:25:05
   */
  private void updateEntity(ProductClassEntity origin, ProductClassSpec spec) {
    if (spec.getDepth() != null) {
      origin.setDepth(spec.getDepth());
    }
    if (StringUtils.isNotBlank(spec.getName())) {
      origin.setName(spec.getName());
      String parentPath = origin.getPath();
      parentPath =
          parentPath.substring(0, parentPath.lastIndexOf(CommonConstant.PRO_CLASS_PATH_SEPARATOR));
      origin.setPath(parentPath + CommonConstant.PRO_CLASS_PATH_SEPARATOR + spec.getName());
    }
    if (spec.getParentId() != null) {
      origin.setParentId(spec.getParentId());
    }
    if (spec.getDepth() != null) {
      origin.setDepth(spec.getDepth());
    }
  }
}
