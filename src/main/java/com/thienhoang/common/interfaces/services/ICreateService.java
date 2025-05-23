package com.thienhoang.common.interfaces.services;

import com.thienhoang.common.interfaces.persistence.IPersistenceProvider;
import com.thienhoang.common.models.HeaderContext;
import com.thienhoang.common.utils.FnCommon;
import com.thienhoang.common.utils.GenericTypeUtils;
import java.util.function.BiFunction;
import org.apache.logging.log4j.util.TriConsumer;

public interface ICreateService<E, ID, RES, REQ>
    extends IResponseMapper<E, RES>, IPersistenceProvider<E, ID> {

  /**
   * Tạo entity mới từ request và lưu vào database. Có hỗ trợ validation và mapping tùy chỉnh.
   *
   * @param context Header context (chứa thông tin người dùng, locale, ...)
   * @param request Dữ liệu từ client gửi lên
   * @param validationCreateHandler Hàm callback kiểm tra dữ liệu đầu vào (có thể throw lỗi)
   * @param mappingEntityHandler Hàm callback để gán dữ liệu tùy chỉnh vào entity
   * @return Entity sau khi được lưu vào database
   */
  default RES create(
      HeaderContext context,
      REQ request,
      TriConsumer<HeaderContext, E, REQ> validationCreateHandler,
      TriConsumer<HeaderContext, E, REQ> mappingEntityHandler,
      TriConsumer<HeaderContext, E, REQ> postHandler,
      BiFunction<HeaderContext, E, RES> mappingResponseHandler) {

    E entity = GenericTypeUtils.getNewInstance(this); // Tạo entity mới bằng Reflection

    if (validationCreateHandler != null) {
      validationCreateHandler.accept(context, entity, request);
    }

    if (mappingEntityHandler != null) {
      mappingEntityHandler.accept(context, entity, request); // Gọi mapping tùy chỉnh
    }

    entity = getCrudPersistence().create(context, entity);

    postHandler.accept(context, entity, request);

    if (mappingResponseHandler == null) {
      throw new IllegalArgumentException("mappingResponseHandler must not be null");
    }
    return mappingResponseHandler.apply(context, entity); // Lưu entity vào DB
  }

  /** Hàm tạo mặc định nếu không truyền vào hàm validate/mapping riêng. */
  default RES create(HeaderContext context, REQ request) {
    return create(
        context,
        request,
        this::validateCreateRequest,
        this::mappingCreateEntity,
        this::postCreateHandler,
        this::mappingResponse);
  }

  /** Hàm validate mặc định (không làm gì) — override trong implementation nếu cần. */
  default void validateCreateRequest(HeaderContext context, E entity, REQ request) {}

  /** Hàm mapping mặc định (không làm gì) — override nếu cần xử lý riêng. */
  default void mappingCreateEntity(HeaderContext context, E entity, REQ request) {
    FnCommon.copyProperties(entity, request); // Copy các field giống nhau từ request sang entity
  }

  default void postCreateHandler(HeaderContext context, E entity, REQ request) {}
}
