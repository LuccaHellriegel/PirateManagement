package com.arrr.piratery.commons.base.mixins.entity;

import com.arrr.piratery.commons.base.exceptions.EntityNotFoundException;
import com.arrr.piratery.commons.base.mixins.core.GetEntityClass;
import com.arrr.piratery.commons.base.mixins.core.GetRepository;
import com.arrr.piratery.commons.base.types.Entity;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetEntityMixin<E extends Entity>
    extends GetRepository<E>, GetEntityClass<E> {

  default Mono<E> get(String entityId) {
    return getRepository().findById(entityId)
        .switchIfEmpty(Mono.error(new EntityNotFoundException(getEntityClass(), entityId)));
  }

  default Flux<E> get(Collection<String> ids) {
    return validateExistence(ids).flatMapMany(Flux::fromIterable);
  }

  default Flux<E> getAll() {
    return getRepository().findAll();
  }

  default Mono<List<E>> validateExistence(Collection<String> ids) {
    return getRepository().findAllById(ids).collectList().map(entityList -> {
      if (entityList.size() != ids.size()) {
        throw new EntityNotFoundException(getEntityClass(), ids.stream()
            .filter(id -> entityList.stream().noneMatch(entity -> entity.getId().equals(id)))
            .collect(Collectors.toList()));
      }
      return entityList;
    });
  }

  default <I> Mono<I> validateExistence(Collection<String> ids, I result) {
    return validateExistence(ids).thenReturn(result);
  }

  default Mono<Void> validateExistence(String id) {
    return getRepository().existsById(id).flatMap(exists -> {
          if (!exists) {
            return Mono.error(new EntityNotFoundException(getEntityClass(), id));
          }
          return Mono.empty();
        }
    );
  }

  default <I> Mono<I> validateExistence(String id, I result) {
    return validateExistence(id).thenReturn(result);
  }
}
