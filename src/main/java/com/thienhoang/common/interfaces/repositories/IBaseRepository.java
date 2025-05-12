package com.thienhoang.common.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IBaseRepository<E, ID> extends JpaSpecificationExecutor<E>, JpaRepository<E, ID> {}
