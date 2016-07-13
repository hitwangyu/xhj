package com.xhj.service.img.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xhj.common.QueryResult;
import com.xhj.common.RestResponse;
import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.dal.entity.ImgEntity;
import com.xhj.manage.img.IImgManage;
import com.xhj.query.ImgQuery;
import com.xhj.service.img.IImgService;
import com.xhj.spec.ImgSpec;
import com.xhj.util.ImgUtil;
import com.xhj.util.LogFactory;
import com.xhj.util.LogFactory.Log;

@Service
public class ImgServiceImpl implements IImgService {

  private final Log logger = LogFactory.getLog(ImgServiceImpl.class);

  @Autowired
  private IImgManage imgManage;

  @Override
  public RestResponse getById(Integer id) {
    return RestResponse.successResponse(ImgUtil.toSpec(imgManage.getById(id)));
  }

  @Override
  public RestResponse getListByQuery(ImgQuery query) {
    List<ImgEntity> imgList = imgManage.getListByQuery(query);
    if (CollectionUtils.isNotEmpty(imgList)) {
      query.setTotalSize(imgManage.getCountByQuery(query));
      query.calTotalPage();
    } else {
      query.setTotalSize(0);
      query.setTotalPage(1);
    }
    QueryResult<ImgQuery, ImgSpec> result = new QueryResult<>();
    result.setQueryCondition(query);
    result.setTarget(ImgUtil.toSpecList(imgList));
    return RestResponse.successResponse(result);
  }

  @Override
  public RestResponse deleteById(Integer id) {
    imgManage.deleteById(id);
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse add(ImgSpec spec) {
    ImgEntity img = ImgUtil.toEntity(spec);
    imgManage.add(img);
    return RestResponse.successResponse(true);
  }

  @Override
  public RestResponse queryAllTypeName() {
    return RestResponse.successResponse(ImgType.getAllTypeName());
  }

  @Override
  public RestResponse update(ImgSpec spec) {
    ImgEntity img = imgManage.getById(spec.getId());
    if (img == null) {
      return RestResponse.errorResponse("图片不存在");
    }
    updateEntity(img, spec);
    imgManage.update(img);
    return RestResponse.successResponse(true);
  }

  private void updateEntity(ImgEntity img, ImgSpec spec) {
    img.setId(spec.getId());
    if (StringUtils.isNotBlank(spec.getDescription())) {
      img.setDescription(spec.getDescription());
    }
    if (StringUtils.isNotBlank(spec.getLinkAddr())) {
      img.setLinkAddr(spec.getLinkAddr());
    }
    if (spec.getWeight() != null) {
      img.setWeight(spec.getWeight());
    }
    if (spec.getType() != null) {
      img.setType(spec.getType());
    }
  }
}
