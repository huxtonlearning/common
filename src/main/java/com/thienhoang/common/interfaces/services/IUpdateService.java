package com.thienhoang.common.interfaces.services;

import com.thienhoang.common.interfaces.repositories.IJpaRepositoryProvider;
import com.thienhoang.common.models.HeaderContext;
import com.thienhoang.common.utils.FnCommon;
import com.thienhoang.common.utils.GenericTypeUtils;
import com.thienhoang.common.utils.functions.QuadConsumer;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import org.apache.logging.log4j.util.TriConsumer;

public interface IUpdateService<E, ID, RES, REQ>
    extends IJpaRepositoryProvider<E, ID>, IResponseMapper<E, RES>, IGetEntityService<E, ID> {
  /**
   * Cập nhật entity hiện có theo ID, có validate và mapping tuỳ chỉnh.
   *
   * @param context Header context
   * @param id ID của entity
   * @param request Request chứa thông tin cần cập nhật
   * @param validationHandler Hàm kiểm tra hợp lệ trước khi update
   * @param mappingHandler Hàm mapping dữ liệu request vào entity
   * @return Entity đã cập nhật
   */
  default RES update(
      HeaderContext context,
      ID id,
      REQ request,
      QuadConsumer<HeaderContext, ID, E, REQ> validationHandler,
      TriConsumer<HeaderContext, E, REQ> mappingHandler,
      BiConsumer<HeaderContext, E> mappingAuditingHandler,
      QuadConsumer<HeaderContext, E, ID, REQ> postHandler,
      BiFunction<HeaderContext, E, RES> mappingResponseHandler) {
    E entity = getEntityById(context, id); // Lấy entity từ DB, nếu không có thì ném lỗi 404

    if (validationHandler != null) {
      validationHandler.accept(context, id, entity, request); // Kiểm tra hợp lệ
    }

    if (mappingHandler != null) {
      mappingHandler.accept(context, entity, request); // Gọi hàm mapping tùy chỉnh
    }

    if (mappingAuditingHandler != null) {
      mappingAuditingHandler.accept(context, entity); // Gọi mapping tùy chỉnh
    }

    entity = getJpaRepository().save(entity);

    if (postHandler != null) {
      postHandler.accept(context, entity, id, request);
    }

    if (mappingResponseHandler == null) {
      throw new IllegalArgumentException("mappingResponseHandler must not be null");
    }

    return mappingResponseHandler.apply(context, entity); // Lưu lại vào DB
  }

  /** Cập nhật mặc định nếu không cần validate/mapping riêng. */
  default RES update(HeaderContext context, ID id, REQ request) {
    return update(
        context,
        id,
        request,
        this::validateUpdateRequest,
        this::mappingUpdateEntity,
        this::mappingUpdateAuditingEntity,
        this::postHandler,
        this::mappingResponse);
  }

  // Validate mặc định khi update
  default void validateUpdateRequest(HeaderContext context, ID id, E entity, REQ request) {}

  // Mapping mặc định khi update
  default void mappingUpdateEntity(HeaderContext context, E entity, REQ request) {
    FnCommon.copyProperties(entity, request); // Gán dữ liệu chung từ request
  }

  default void mappingUpdateAuditingEntity(HeaderContext context, E entity) {
    if (context != null) {
      GenericTypeUtils.updateData(entity, "modifierId", context.getUserId());
    }
  }

  default void postHandler(HeaderContext context, E entity, ID id, REQ request) {}
}
