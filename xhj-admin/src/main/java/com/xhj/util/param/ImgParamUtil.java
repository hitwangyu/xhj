package com.xhj.util.param;

import org.apache.commons.lang3.StringUtils;

import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.param.img.ImgAddParam;
import com.xhj.param.img.ImgUpdateParam;
import com.xhj.spec.ImgSpec;

public class ImgParamUtil {
  public static ImgSpec toSpec(ImgAddParam param) {
    ImgSpec spec = new ImgSpec();
    spec.setImgAddr(param.getImgAddr());
    spec.setDescription(param.getDescription());
    spec.setWeight(param.getWeight());
    if (StringUtils.isNotBlank(param.getLinkAddr())) {
      spec.setLinkAddr(param.getLinkAddr());
    }
    if (StringUtils.isNotBlank(param.getTypeName())) {
      spec.setTypeName(param.getTypeName());
      ImgType imgType = ImgType.getByName(param.getTypeName());
      if (imgType != null) {
        spec.setType(imgType.getType());
      }
    }
    return spec;
  }

  public static ImgSpec toSpec(ImgUpdateParam param) {
    ImgSpec spec = new ImgSpec();
    spec.setId(param.getId());
    spec.setDescription(param.getDescription());
    spec.setWeight(param.getWeight());
    if (StringUtils.isNotBlank(param.getLinkAddr())) {
      spec.setLinkAddr(param.getLinkAddr());
    }
    if (StringUtils.isNotBlank(param.getTypeName())) {
      spec.setTypeName(param.getTypeName());
      ImgType imgType = ImgType.getByName(param.getTypeName());
      if (imgType != null) {
        spec.setType(imgType.getType());
      }
    }
    return spec;
  }
}
