package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import reactor.core.publisher.Mono;

public interface CreateDOMixin<PO extends PersistenceObject, DO extends DomainObject>
    extends ValidateDOMixin<PO, DO> {

  default Mono<DO> create(PO persistenceObject) {
    persistenceObject.setId(null);
    return toDO(persistenceObject).flatMap(this::validatedSave);
  }

}
