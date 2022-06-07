package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import reactor.core.publisher.Mono;

public interface SaveDOMixin<PO extends PersistenceObject, DO extends DomainObject>
    extends GetRepository<PO>, NormalisationMixin<PO, DO> {

  default Mono<DO> save(DO domainObject) {
    return getRepository().save(toPO(domainObject))
        .thenReturn(domainObject);
  }

}
