package com.arrr.piratery.commons.base.mixins.controllers;


import com.arrr.piratery.commons.base.controllers.BaseController;
import com.arrr.piratery.commons.base.mixins.entity.GetEntityMixin;
import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface GetEntityControllerMixin<E extends Entity> extends BaseController {

  GetEntityMixin<E> getService();

  default Mono<ResponseEntity<Flux<E>>> getAll() {
    return Mono.just(ResponseEntity.ok(getService().getAll()));
  }

  default Mono<ResponseEntity<E>> get(String id) {
    return getService().get(id).map(ResponseEntity::ok);
  }
}

