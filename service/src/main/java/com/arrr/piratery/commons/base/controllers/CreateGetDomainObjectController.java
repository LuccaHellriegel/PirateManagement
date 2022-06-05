package com.arrr.piratery.commons.base.controllers;

import com.arrr.piratery.commons.base.services.EntityService;
import com.arrr.piratery.commons.base.services.NormalisingEntityService;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public class CreateGetDomainObjectController<PO extends PersistenceObject, DO extends DomainObject> extends
    GetDomainObjectController<PO, DO> {

  public final String context;

  public CreateGetDomainObjectController(
      NormalisingEntityService<PO, DO> service,
      String context) {
    super(service);
    this.context = context;
  }

  public URI toURI(DO domainObject) {
    return URI.create(BASE_PATH + "/" + context + "/" + domainObject.getId());
  }

  @PostMapping()
  public Mono<ResponseEntity<DO>> create(@RequestBody @Validated PO entity) {
    return service.upsert(entity).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }
}
