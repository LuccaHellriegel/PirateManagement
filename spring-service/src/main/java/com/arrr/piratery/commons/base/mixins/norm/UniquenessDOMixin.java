package com.arrr.piratery.commons.base.mixins.norm;

import com.arrr.piratery.commons.base.exceptions.DuplicateEntityException;
import com.arrr.piratery.commons.base.mixins.core.GetEntityClass;
import com.arrr.piratery.commons.base.types.Entity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UniquenessDOMixin<PO extends Entity, DO extends Entity> extends
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
