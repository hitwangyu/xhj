package com.xhj.service.img;

import com.xhj.common.RestResponse;
import com.xhj.query.ImgQuery;
import com.xhj.spec.ImgSpec;

public interface IImgService {

  public RestResponse getById(Integer id);

  public RestResponse getListByQuery(ImgQuery query);

  public RestResponse deleteById(Integer id);

  public RestResponse add(ImgSpec spec);

  public RestResponse queryAllTypeName();

  public RestResponse update(ImgSpec spec);

}
