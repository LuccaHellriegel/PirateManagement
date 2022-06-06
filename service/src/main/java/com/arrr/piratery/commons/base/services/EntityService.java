package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.Entity;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@Getter
@Slf4j
public abstract class EntityService<E extends Entity> extends GetService<E> {

  public EntityService(Class<E> entityClass, ReactiveCrudRepository<E, String> repository) {
    super(entityClass, repository);
  }

  public abstract Mono<E> validate(E entity);

  public Mono<E> validatedSave(E entity) {
    return validate(entity)
        .flatMap(repository::save);
  }

  public Mono<E> save(E entity) {
    return repository.save(entity);
  }
}
