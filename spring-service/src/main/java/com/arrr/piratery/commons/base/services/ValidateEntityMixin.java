package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ValidateEntityMixin<E extends Entity>
extends GetRepository<E>
{

  Mono<E> validate(E entity);

  default Mono<E> validatedSave(E entity) {
    return validate(entity)
        .flatMap(getRepository()::save);
  }


}
