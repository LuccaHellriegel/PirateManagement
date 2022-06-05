package com.arrr.piratery.commons.base.validation;

import com.arrr.piratery.commons.base.types.PersistenceObject;
import com.arrr.piratery.commons.base.error.EntityError;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class BaseEntityValidation<E extends PersistenceObject> implements EntityValidation<E> {

  protected final EntityError<E> entityError;
  protected final ReactiveCrudRepository<E, String> repository;

  /**
   * The default validation always returns true.
   */
  public Mono<Boolean> isUnique(E entity) {
    return Mono.just(true);
  }

  public Mono<E> validateUniqueness(E entity) {
    return isUnique(entity).map(isUnique -> {
      if (!isUnique) {
        throw entityError.duplicate();
      }
      return entity;
    });
  }

  /**
   * The default validation always returns true.
   */
  public Mono<Boolean> hasValidProperties(E entity) {
    return Mono.just(true);
  }

  public Mono<E> validateEntityProps(E entity) {
    return hasValidProperties(entity).map(isValid -> {
      if (!isValid) {
        throw entityError.invalid(entity);
      }
      return entity;
    });
  }

  /**
   * Executes all available validation methods. Not overriden validation methods return true.
   */
  @Override
  public Mono<E> validate(E entity) {
    return validateEntityProps(entity).flatMap(this::validateUniqueness);
  }

  @Override
  public Mono<List<E>> validateExistence(Collection<String> ids) {
    return repository.findAllById(ids).collectList().map(entityList -> {
      if (entityList.size() != ids.size()) {
        throw entityError.notFound(ids.stream()
            .filter(id -> entityList.stream().noneMatch(entity -> entity.getId().equals(id)))
            .collect(Collectors.toList()));
      }
      return entityList;
    });
  }

  @Override
  public Mono<Void> validateExistence(String id) {
    return repository.existsById(id).flatMap(exists -> {
          if (!exists) {
            return Mono.error(entityError.notFound(id));
          }
          return Mono.empty();
        }
    );
  }
}
