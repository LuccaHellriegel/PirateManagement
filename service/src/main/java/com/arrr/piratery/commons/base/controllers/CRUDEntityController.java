package com.arrr.piratery.commons.base.controllers;

import com.arrr.piratery.commons.base.services.EntityService;
import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public abstract class CRUDEntityController<E extends Entity> extends CreateGetEntityController<E> {

  public CRUDEntityController(EntityService<E> service, String context) {
    super(service, context);
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<E>> update(
      @RequestBody @Validated E entity,
      @PathVariable String id) {
    entity.setId(id);
    return service.save(entity).map(ResponseEntity::ok);
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    return service.delete(id).thenReturn(ResponseEntity.noContent().build());
  }
}
