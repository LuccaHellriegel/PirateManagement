package com.arrr.piratery.commons.base.mixins.core;

import com.arrr.piratery.commons.base.types.Entity;
import reactor.core.publisher.Mono;

public interface Normalisation<PO extends Entity, DO extends Entity> {

  PO toPO(DO domainObject);

  Mono<DO> toDO(PO persistenceObject);

}
