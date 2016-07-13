package com.xhj.util;

import java.util.ArrayList;
import java.util.List;

import com.xhj.constant.ImgConstant.ImgType;
import com.xhj.dal.entity.ImgEntity;
import com.xhj.spec.ImgSpec;

public class ImgUtil {
  public static ImgSpec toSpec(ImgEntity img) {
    ImgSpec spec = new ImgSpec();
    spec.setId(img.getId());
    spec.setImgAddr(img.getImgAddr());
    spec.setLinkAddr(img.getLinkAddr());
    spec.setTypeName(ImgType.getByCode(img.getType()).getName());
    spec.setType(img.getType());
    spec.setWeight(img.getWeight());
    spec.setDescription(img.getDescription());
    return spec;
  }

  public static List<ImgSpec> toSpecList(List<ImgEntity> imgList) {
    List<ImgSpec> specList = new ArrayList<>(imgList.size());
    for (ImgEntity img : imgList) {
      specList.add(toSpec(img));
    }
    return specList;
  }

  public static ImgEntity toEntity(ImgSpec spec) {
    ImgEntity img = new ImgEntity();
    img.setImgAddr(spec.getImgAddr());
    img.setLinkAddr(spec.getLinkAddr());
    img.setDescription(spec.getDescription());
    img.setWeight(spec.getWeight());
    ImgType type = ImgType.getByName(spec.getTypeName());
    if (type != null) {
      img.setType(type.getType());
    }
    return img;
  }
}
