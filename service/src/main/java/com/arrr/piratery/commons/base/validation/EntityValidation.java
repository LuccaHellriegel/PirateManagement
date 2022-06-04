package com.arrr.piratery.commons.base.validation;

import java.util.Collection;
import java.util.List;
import reactor.core.publisher.Mono;

public interface EntityValidation<E> {

  Mono<Boolean> isUnique(E entity);

  Mono<E> validateUniqueness(E entity);

  Mono<Boolean> hasValidProperties(E entity);

  Mono<E> validateEntityProps(E entity);

  Mono<E> validate(E entity);

  /**
   * Accurate validation can only be generically executed if we get all the entities, so we can just
   * return them for convenience and performance reasons.
   */
  Mono<List<E>> validateExistence(Collection<String> ids);

  Mono<Void> validateExistence(String id);
}
