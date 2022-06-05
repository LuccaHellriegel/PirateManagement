package com.arrr.piratery.commons.base.controllers;

import com.arrr.piratery.commons.base.services.EntityService;
import com.arrr.piratery.commons.base.services.NormalisingEntityService;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.Entity;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class GetDomainObjectController<PO extends PersistenceObject, DO extends DomainObject> extends
    BaseController {

  public final NormalisingEntityService<PO, DO> service;

  @GetMapping()
  public Mono<ResponseEntity<Flux<DO>>> getAll() {
    return Mono.just(ResponseEntity.ok(service.getAllDOs()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<DO>> get(@PathVariable String id) {
    return service.getDO(id).map(ResponseEntity::ok);
  }

}
