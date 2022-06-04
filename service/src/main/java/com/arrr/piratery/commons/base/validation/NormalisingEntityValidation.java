package com.arrr.piratery.commons.base.validation;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import reactor.core.publisher.Mono;

public interface NormalisingEntityValidation<PO extends PersistenceObject, DO extends DomainObject> extends
    EntityValidation<PO> {

  Mono<Boolean> isUnique(DO domainObject);

  Mono<DO> validateUniqueness(DO domainObject);

  Mono<Boolean> hasValidProperties(DO domainObject);

  Mono<DO> validateEntityProps(DO domainObject);

  Mono<DO> validate(DO domainObject);
  
}
