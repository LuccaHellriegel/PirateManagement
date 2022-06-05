package com.arrr.piratery.commons.base;


import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.net.URI;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * We provide the most common endpoints: get entity and get all entities. Creation, updating and
 * deletion are usually more involved.
 */
@AllArgsConstructor
abstract public class BaseEntityController<E extends PersistenceObject> extends BaseController {

  public final EntityRepository<E> service;
  public final String context;

  public URI toURI(E entity) {
    return URI.create(BASE_PATH + "/" + context + "/" + entity.getId());
  }

  @GetMapping()
  public Mono<ResponseEntity<Flux<E>>> getAll() {
    return Mono.just(ResponseEntity.ok(service.getAll()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<E>> get(@PathVariable String id) {
    return service.get(id).map(ResponseEntity::ok);
  }
}

