package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.exceptions.EntityNotFoundException;
import com.arrr.piratery.commons.base.types.Entity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
@AllArgsConstructor
@Slf4j
public abstract class EntityService<E extends Entity> {

  protected final Class<E> entityClass;
  protected final ReactiveCrudRepository<E, String> repository;

  public Mono<E> get(String entityId) {
    return repository.findById(entityId)
        .switchIfEmpty(Mono.error(new EntityNotFoundException(entityClass, entityId)));
  }

  public Flux<E> get(Collection<String> ids) {
    return validateExistence(ids).flatMapMany(Flux::fromIterable);
  }

  public Flux<E> getAll() {
    return repository.findAll();
  }

  public Mono<List<E>> validateExistence(Collection<String> ids) {
    return repository.findAllById(ids).collectList().map(entityList -> {
      if (entityList.size() != ids.size()) {
        throw new EntityNotFoundException(entityClass, ids.stream()
            .filter(id -> entityList.stream().noneMatch(entity -> entity.getId().equals(id)))
            .collect(Collectors.toList()));
      }
      return entityList;
    });
  }

  public Mono<Void> validateExistence(String id) {
    return repository.existsById(id).flatMap(exists -> {
          if (!exists) {
            return Mono.error(new EntityNotFoundException(entityClass, id));
          }
          return Mono.empty();
        }
    );
  }

  public Mono<Void> delete(String entityId) {
    return validateExistence(entityId)
        .then(repository.deleteById(entityId));
  }

  public abstract Mono<E> validate(E entity);

  public Mono<E> save(E entity) {
    return validate(entity)
        .flatMap(repository::save);
  }
}
