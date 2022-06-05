package com.arrr.piratery.commons.base.normalisation;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Getter
public class BaseEntityNormalisation<PO extends PersistenceObject, DO extends DomainObject> implements
    EntityNormalisation<PO, DO> {

  protected final EntityMapper<PO, DO> mapper;

  @Override
  public PO toPO(DO domainObject) {
    return mapper.toPO(domainObject);
  }

  @Override
  public Mono<DO> toDO(PO persistenceObject) {
    return Mono.just(mapper.partialToDO(persistenceObject));
  }
}
