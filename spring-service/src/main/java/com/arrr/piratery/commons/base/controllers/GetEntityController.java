package com.arrr.piratery.commons.base.controllers;


import com.arrr.piratery.commons.base.services.entity.GetEntityMixin;
import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;


public interface GetEntityController<E extends Entity> extends BaseController {

    GetEntityMixin<E> getService();

    String getContext();

    default URI toURI(E entity) {
        return URI.create(BASE_PATH + "/" + getContext() + "/" + entity.getId());
    }

    @GetMapping()
    default Mono<ResponseEntity<Flux<E>>> getAll() {
        return Mono.just(ResponseEntity.ok(getService().getAll()));
    }

    @GetMapping("/{id}")
    default Mono<ResponseEntity<E>> get(@PathVariable String id) {
        return getService().get(id).map(ResponseEntity::ok);
    }
}

