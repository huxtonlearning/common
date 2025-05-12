package com.thienhoang.common.specifications.services;

import com.thienhoang.common.models.HeaderContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface IGetEntityService<E, ID> extends IJpaRepositoryProvider<E, ID> {
  /** Tìm entity theo ID, ném lỗi 404 nếu không tìm thấy. */
  default E getEntityById(HeaderContext context, ID id) {
    return getJpaRepository()
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
  }
}
