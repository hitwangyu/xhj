package com.xhj.util.query;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.param.img.ImgQueryParam;
import com.xhj.query.ImgQuery;

public class ImgQueryUtil {
  public static ImgQuery toQuery(ImgQueryParam param) {
    ImgQuery query = new ImgQuery();
    if (StringUtils.isNotBlank(param.getTypeName())) {
      ImgType imgType = ImgType.getByName(param.getTypeName());
      if (imgType != null) {
        query.setType(imgType.getType());
      }
    }
    if (StringUtils.isNotBlank(param.getDescription())) {
      query.setLikeDescription(param.getDescription());
    }
    if (param.getId() != null) {
      query.setId(param.getId());
    }
    if (param.getPageNo() == null || param.getPageSize() == null) {
      query.setIsQueryAll(Boolean.TRUE);
    } else {
      query.setPageSize(param.getPageSize());
      query.setPageNo(param.getPageNo());
    }
    return query;
  }
}
