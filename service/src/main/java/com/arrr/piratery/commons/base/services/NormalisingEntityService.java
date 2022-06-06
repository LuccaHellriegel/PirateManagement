package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import lombok.Getter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

@Getter
public abstract class NormalisingEntityService<PO extends PersistenceObject, DO extends DomainObject> extends
    NormalisingGetService<PO, DO> {

  public NormalisingEntityService(
      Class<PO> entityClass,
      ReactiveCrudRepository<PO, String> repository,
      Normalisation<PO, DO> normalisation) {
    super(entityClass, repository, normalisation);
  }

  public abstract Mono<DO> validate(DO domainObject);

  public Mono<DO> validatedSave(DO domainObject) {
    return validate(domainObject)
        .map(normalisation::toPO)
        .flatMap(repository::save)
        .thenReturn(domainObject);
  }

  public Mono<DO> save(DO domainObject) {
    return repository.save(normalisation.toPO(domainObject))
        .thenReturn(domainObject);
  }

}
