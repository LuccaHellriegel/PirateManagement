package com.arrr.piratery.commons.base.services.entity;

import com.arrr.piratery.commons.base.types.Entity;
import reactor.core.publisher.Mono;

public interface CreateEntityMixin<E extends Entity> extends ValidateEntityMixin<E> {

  default Mono<E> create(E persistenceObject) {
    persistenceObject.setId(null);
    return validatedSave(persistenceObject);
  }
}
