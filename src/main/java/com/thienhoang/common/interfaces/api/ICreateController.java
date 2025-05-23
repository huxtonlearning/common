package com.thienhoang.common.interfaces.api;

import com.thienhoang.common.interfaces.services.ICreateService;
import com.thienhoang.common.models.HeaderContext;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICreateController<E, ID, RES, REQ> {

  ICreateService<E, ID, RES, REQ> getCreateService();

  /**
   * Tạo mới một entity.
   *
   * @param context HeaderContext tự động resolve từ request (thường chứa: token, locale, userId...)
   * @param request Dữ liệu gửi lên từ client để tạo entity
   * @return RES Đối tượng trả về sau khi tạo (thường là bản ghi vừa tạo)
   */
  @PostMapping
  default ResponseEntity<RES> create(
      @Parameter(hidden = true) HeaderContext context, @RequestBody REQ request) {
    if (getCreateService() == null) {
      return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }
    return ResponseEntity.ok(getCreateService().create(context, request));
  }
}
