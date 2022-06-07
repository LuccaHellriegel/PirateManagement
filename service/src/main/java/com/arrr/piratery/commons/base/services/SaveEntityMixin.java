package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface SaveEntityMixin<E extends Entity>
    extends GetRepository<E> {

  default Mono<E> save(E entity) {
    return getRepository().save(entity);
  }

}
