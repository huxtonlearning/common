package com.thienhoang.common.interfaces.api;

import com.thienhoang.common.interfaces.services.IDeleteService;
import com.thienhoang.common.models.HeaderContext;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IDeleteController<E, ID> {

  default IDeleteService<E, ID> getDeleteService() {

    return null;
  }

  /**
   * Xoá một entity theo ID.
   *
   * @param context HeaderContext từ request
   * @param id ID của entity cần xoá
   */
  @DeleteMapping(path = "/{id}")
  default ResponseEntity<?> delete(
      @Parameter(hidden = true) HeaderContext context, @PathVariable ID id) {
    if (getDeleteService() == null) {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }
    getDeleteService().delete(context, id);
    return ResponseEntity.noContent().build();
  }
}
