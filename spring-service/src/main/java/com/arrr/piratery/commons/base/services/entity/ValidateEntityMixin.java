package com.arrr.piratery.commons.base.services.entity;

import com.arrr.piratery.commons.base.services.core.GetRepository;
import com.arrr.piratery.commons.base.types.Entity;
import java.util.List;
import java.util.function.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ValidateEntityMixin<E extends Entity>
    extends GetRepository<E> {

  List<Function<E, Mono<E>>> getValidationMethods();

  default Mono<E> validate(E entity) {
    return Flux.fromIterable(getValidationMethods())
        .flatMap(method -> method.apply(entity))
        .then(Mono.just(entity));
  }

  default Mono<E> validatedSave(E entity) {
    return validate(entity)
        .flatMap(getRepository()::save);
  }

}