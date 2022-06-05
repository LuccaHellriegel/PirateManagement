package com.arrr.piratery.commons.base.controllers;

import com.arrr.piratery.commons.base.services.EntityService;
import com.arrr.piratery.commons.base.types.Entity;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public abstract class CreateGetEntityController<E extends Entity> extends GetEntityController<E> {

  public final String context;

  public CreateGetEntityController(EntityService<E> service, String context) {
    super(service);
    this.context = context;
  }

  public URI toURI(E entity) {
    return URI.create(BASE_PATH + "/" + context + "/" + entity.getId());
  }

  @PostMapping()
  public Mono<ResponseEntity<E>> create(@RequestBody @Validated E entity) {
    return service.save(entity).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }
}
