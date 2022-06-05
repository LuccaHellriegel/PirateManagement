package com.arrr.piratery.commons.base.validation;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import reactor.core.publisher.Mono;

public interface NormalisingEntityValidation<PO extends PersistenceObject, DO extends DomainObject> extends
    EntityValidation<PO> {

  Mono<DO> validate(DO domainObject);
  
}
