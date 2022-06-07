package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ValidateDOMixin<PO extends PersistenceObject, DO extends DomainObject>
    extends NormalisationMixin<PO, DO> {

  ReactiveCrudRepository<PO, String> getRepository();

  Mono<DO> validate(DO domainObject);

  default Mono<DO> validatedSave(DO domainObject) {
    return validate(domainObject)
        .map(this::toPO)
        .flatMap(getRepository()::save)
        .thenReturn(domainObject);
  }

}
