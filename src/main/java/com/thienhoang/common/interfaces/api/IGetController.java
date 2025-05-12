package com.thienhoang.common.interfaces.api;

import com.thienhoang.common.interfaces.services.IGetService;
import com.thienhoang.common.models.HeaderContext;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface IGetController<E, ID, RES> {

  IGetService<E, ID, RES> getGetService();

  /**
   * Lấy thông tin một entity theo ID.
   *
   * @param context HeaderContext từ request
   * @param id ID của entity cần lấy
   * @return RES Thông tin entity
   */
  @GetMapping(path = "/{id}")
  default RES getById(
      @Parameter(hidden = true) HeaderContext context,
      @Parameter(description = "ID of the user to retrieve", required = true) @PathVariable ID id) {
    if (getGetService() == null) {
      return null;
    }
    return getGetService().getById(context, id);
  }
}
