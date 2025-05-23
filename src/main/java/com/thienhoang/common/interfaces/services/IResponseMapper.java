package com.thienhoang.common.interfaces.services;

import com.thienhoang.common.models.HeaderContext;
import com.thienhoang.common.utils.FnCommon;
import com.thienhoang.common.utils.GenericTypeUtils;

public interface IResponseMapper<E, RES> {

  default RES mappingResponse(HeaderContext context, E entity) {

    RES res = GenericTypeUtils.getNewInstance(this);

    FnCommon.copyProperties(res, entity);

    return res;
  }
}
