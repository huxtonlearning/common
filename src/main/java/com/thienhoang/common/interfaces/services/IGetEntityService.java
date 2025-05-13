package com.thienhoang.common.interfaces.services;

import com.thienhoang.common.interfaces.persistence.IPersistenceProvider;
import com.thienhoang.common.models.HeaderContext;

public interface IGetEntityService<E, ID> extends IPersistenceProvider<E, ID> {
  /** Tìm entity theo ID, ném lỗi 404 nếu không tìm thấy. */
  default E getEntityById(HeaderContext context, ID id) {
    return getCrudPersistence().getById(id);
  }
}
