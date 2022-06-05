package com.arrr.piratery.commons.base.normalisation;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import reactor.core.publisher.Mono;

public interface EntityNormalisation<PO extends PersistenceObject, DO extends DomainObject> {

  PO toPO(DO domainObject);

  Mono<DO> toDO(PO persistenceObject);


}
