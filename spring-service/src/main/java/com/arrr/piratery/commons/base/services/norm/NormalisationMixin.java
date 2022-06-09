package com.arrr.piratery.commons.base.services.norm;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import reactor.core.publisher.Mono;

public interface NormalisationMixin<PO extends PersistenceObject, DO extends DomainObject> {

  EntityMapper<PO, DO> getMapper();

  default PO toPO(DO domainObject) {
    return getMapper().toPO(domainObject);
  }

  Mono<DO> toDO(PO persistenceObject);

}
