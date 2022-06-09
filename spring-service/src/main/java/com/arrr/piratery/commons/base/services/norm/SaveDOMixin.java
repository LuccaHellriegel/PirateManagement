package com.arrr.piratery.commons.base.services.norm;

import com.arrr.piratery.commons.base.services.core.GetRepository;
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
