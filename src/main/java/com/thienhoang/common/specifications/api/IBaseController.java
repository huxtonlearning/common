package com.thienhoang.common.specifications.api;

import com.thienhoang.common.specifications.services.IBaseService;
import com.thienhoang.common.specifications.services.ICrudService;
import com.thienhoang.common.specifications.services.IGetAllService;

public interface IBaseController<E, ID, RES, REQ>
    extends IGetAllController<E, RES>, ICrudController<E, ID, RES, REQ> {

  IBaseService<E, ID, RES, REQ> getService();

  @Override
  default ICrudService<E, ID, RES, REQ> getCrudService() {
    return getService();
  }

  @Override
  default IGetAllService<E, RES> getGetAllService() {
    return getService();
  }
}
