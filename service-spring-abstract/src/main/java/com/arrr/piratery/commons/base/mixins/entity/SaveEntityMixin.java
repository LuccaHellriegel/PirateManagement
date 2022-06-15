package com.arrr.piratery.commons.base.mixins.entity;

import com.arrr.piratery.commons.base.mixins.core.GetRepository;
import com.arrr.piratery.commons.base.types.Entity;
import reactor.core.publisher.Mono;

public interface SaveEntityMixin<E extends Entity>
    extends GetRepository<E> {

  default Mono<E> save(E entity) {
    return getRepository().save(entity);
  }

}
