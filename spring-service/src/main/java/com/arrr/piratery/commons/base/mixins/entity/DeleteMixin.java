package com.arrr.piratery.commons.base.mixins.entity;

import com.arrr.piratery.commons.base.types.Entity;
import reactor.core.publisher.Mono;

public interface DeleteMixin<E extends Entity> extends GetEntityMixin<E> {

  default Mono<Void> validatedDelete(String id) {
    return validateExistence(id).then(getRepository().deleteById(id));
  }
}
