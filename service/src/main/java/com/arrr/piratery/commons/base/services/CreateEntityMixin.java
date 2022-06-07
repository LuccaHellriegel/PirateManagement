package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.Entity;
import com.arrr.piratery.commons.ports.domain.CrewPO;
import com.arrr.piratery.crew.domain.Crew;
import reactor.core.publisher.Mono;

public interface CreateEntityMixin<E extends Entity> extends ValidateEntityMixin<E> {

  default Mono<E> create(E persistenceObject) {
    persistenceObject.setId(null);
    return validatedSave(persistenceObject);
  }
}
