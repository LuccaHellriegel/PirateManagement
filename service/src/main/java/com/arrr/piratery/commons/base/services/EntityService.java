package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.error.EntityError;
import com.arrr.piratery.commons.base.types.Entity;
import com.arrr.piratery.commons.base.validation.EntityValidation;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
@AllArgsConstructor
@Slf4j
public class EntityService<E extends Entity> {

  protected final EntityError entityError;
  protected final EntityValidation<E> entityValidation;
  protected final ReactiveCrudRepository<E, String> repository;

  public Mono<E> get(String entityId) {
    return repository.findById(entityId)
        .switchIfEmpty(Mono.error(entityError.notFound(entityId)));
  }

  public Flux<E> get(Collection<String> ids) {
    return entityValidation.validateExistence(ids).flatMapMany(Flux::fromIterable);
  }

  public Flux<E> getAll() {
    return repository.findAll();
  }

  public Mono<Void> delete(String entityId) {
    return entityValidation.validateExistence(entityId)
        .then(repository.deleteById(entityId));
  }

  public Mono<E> save(E entity) {
    return entityValidation.validate(entity)
        .flatMap(repository::save);
  }
}
