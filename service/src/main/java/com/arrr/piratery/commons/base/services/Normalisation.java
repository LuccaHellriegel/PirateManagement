package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.core.publisher.Mono;

@Getter
@AllArgsConstructor
abstract public class Normalisation<PO extends PersistenceObject, DO extends DomainObject> {

  protected final EntityMapper<PO, DO> mapper;

  public PO toPO(DO domainObject) {
    return mapper.toPO(domainObject);
  }

  public abstract Mono<DO> toDO(PO persistenceObject);
}
