package com.arrr.piratery.commons.base.controllers;


import com.arrr.piratery.commons.base.services.EntityService;
import com.arrr.piratery.commons.base.types.Entity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public abstract class GetEntityController<E extends Entity> extends BaseController {

  public final EntityService<E> service;

  @GetMapping()
  public Mono<ResponseEntity<Flux<E>>> getAll() {
    return Mono.just(ResponseEntity.ok(service.getAll()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<E>> get(@PathVariable String id) {
    return service.get(id).map(ResponseEntity::ok);
  }
}

