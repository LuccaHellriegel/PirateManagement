package com.arrr.piratery.commons.base.controllers;

import com.arrr.piratery.commons.base.services.NormalisingEntityService;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public class CRUDDomainObjectController<PO extends PersistenceObject, DO extends DomainObject> extends
    CreateGetDomainObjectController<PO, DO> {

  public CRUDDomainObjectController(
      NormalisingEntityService<PO, DO> service,
      String context) {
    super(service, context);
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<DO>> update(
      @RequestBody @Validated PO entity,
      @PathVariable String id) {
    entity.setId(id);
    return service.upsert(entity).map(ResponseEntity::ok);
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
    return service.delete(id).thenReturn(ResponseEntity.noContent().build());
  }
}
