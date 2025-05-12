package com.thienhoang.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IJpaRepositoryProvider<E, ID> {

  JpaRepository<E, ID> getJpaRepository();
}
