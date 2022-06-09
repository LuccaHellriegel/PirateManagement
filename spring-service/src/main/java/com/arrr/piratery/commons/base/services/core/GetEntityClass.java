package com.arrr.piratery.commons.base.services.core;

import com.arrr.piratery.commons.base.types.Entity;

public interface GetEntityClass<E extends Entity> {

  Class<E> getEntityClass();

}