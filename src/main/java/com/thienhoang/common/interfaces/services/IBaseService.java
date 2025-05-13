package com.thienhoang.common.interfaces.services;

import com.thienhoang.common.interfaces.persistence.IBasePersistence;
import com.thienhoang.common.interfaces.persistence.ICrudPersistence;

public interface IBaseService<E, ID, RES, REQ>
    extends ICrudService<E, ID, RES, REQ>, IGetAllService<E, RES> {

  IBasePersistence getPersistence();

  @Override
  default ICrudPersistence getCrudPersistence() {
    return getPersistence();
  }
}
