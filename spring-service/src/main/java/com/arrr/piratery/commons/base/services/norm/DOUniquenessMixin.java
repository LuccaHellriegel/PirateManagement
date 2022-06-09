package com.arrr.piratery.commons.base.services.norm;

import com.arrr.piratery.commons.base.exceptions.DuplicateEntityException;
import com.arrr.piratery.commons.base.services.core.GetEntityClass;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DOUniquenessMixin<PO extends PersistenceObject, DO extends DomainObject> extends
    GetEntityClass<PO> {

  Flux<PO> getSame(DO domainObject);

  default Mono<DO> validateUniqueness(DO domainObject) {
    return getSame(domainObject)
        .filter(po -> !po.getId().equals(domainObject.getId()))
        .hasElements()
        .map(hasElements -> {
          if (hasElements) {
            throw new DuplicateEntityException(getEntityClass());
          }
          return domainObject;
        });
  }

}
