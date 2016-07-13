package com.xhj.util;

import java.util.ArrayList;
import java.util.List;

import com.xhj.dal.entity.ImgEntity;
import com.xhj.spec.ImgSpec;

public class ImgUtil {
  public static ImgSpec toSpec(ImgEntity img) {
    ImgSpec spec = new ImgSpec();
    spec.setImgAddr(img.getImgAddr());
    spec.setLinkAddr(img.getLinkAddr());
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
}
