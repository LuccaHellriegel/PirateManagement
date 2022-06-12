package com.arrr.piratery.commons.base.mixins.entity;

import com.arrr.piratery.commons.base.exceptions.DuplicateEntityException;
import com.arrr.piratery.commons.base.mixins.core.GetEntityClass;
import com.arrr.piratery.commons.base.types.Entity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UniquenessMixin<E extends Entity> extends GetEntityClass<E> {

  Flux<E> getSame(E entity);

  default Mono<E> validateUniqueness(E entity) {
    return getSame(entity)
        .filter(po -> !po.getId().equals(entity.getId()))
        .hasElements()
        .map(hasElements -> {
          if (hasElements) {
            throw new DuplicateEntityException(getEntityClass());
          }
          return entity;
        });
  }

}
