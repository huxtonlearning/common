package com.thienhoang.common.interfaces.persistence;

public interface IPersistenceProvider<E, ID> {

  ICrudPersistence<E, ID> getCrudPersistence();
}
