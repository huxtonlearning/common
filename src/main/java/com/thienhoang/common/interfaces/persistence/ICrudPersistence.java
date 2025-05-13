package com.thienhoang.common.interfaces.persistence;

import com.thienhoang.common.models.HeaderContext;
import com.thienhoang.common.utils.GenericTypeUtils;
import jakarta.transaction.Transactional;
import java.util.function.BiConsumer;
import org.springframework.data.repository.CrudRepository;

public interface ICrudPersistence<E, ID> {

  CrudRepository<E, ID> getCrudRepository();

  default void mappingCreateAuditingEntity(HeaderContext context, E entity) {
    if (context != null) {
      GenericTypeUtils.updateData(entity, "creatorId", context.getUserId());
      GenericTypeUtils.updateData(entity, "modifierId", context.getUserId());
    }
  }

  @Transactional
  default E create(HeaderContext context, E model) {

    return create(context, model, this::mappingCreateAuditingEntity);
  }

  @Transactional
  default E create(
      HeaderContext context, E entity, BiConsumer<HeaderContext, E> mappingCreateAuditingEntity) {

    if (mappingCreateAuditingEntity != null) {
      mappingCreateAuditingEntity.accept(context, entity);
    }
    return entity;
  }

  @Transactional
  default E update(HeaderContext context, ID id, E entity) {

    return update(context, id, entity, this::mappingUpdateAuditingEntity);
  }

  @Transactional
  default E update(
      HeaderContext context,
      ID id,
      E entity,
      BiConsumer<HeaderContext, E> mappingUpdateAuditingEntity) {

    if (mappingUpdateAuditingEntity != null) {
      mappingUpdateAuditingEntity.accept(context, entity);
    }

    return entity;
  }

  default void mappingUpdateAuditingEntity(HeaderContext context, E entity) {
    if (context != null) {
      GenericTypeUtils.updateData(entity, "modifierId", context.getUserId());
    }
  }

  default E getById(ID id) {

    return getCrudRepository().findById(id).orElse(null);
  }

  default void delete(E entity) {
    getCrudRepository().delete(entity);
  }
}
