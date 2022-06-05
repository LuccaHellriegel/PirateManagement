package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.error.EntityError;
import com.arrr.piratery.commons.base.normalisation.EntityNormalisation;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import com.arrr.piratery.commons.base.validation.NormalisingEntityValidation;
import com.arrr.piratery.ports.domain.crew.CrewMapper;
import java.util.Collection;
import lombok.Getter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public class NormalisingEntityService<PO extends PersistenceObject, DO extends DomainObject> extends
    EntityService<PO> {

  protected final EntityNormalisation<PO, DO> entityNormalisation;
  protected final NormalisingEntityValidation<PO, DO> entityValidation;

  public NormalisingEntityService(
      EntityNormalisation<PO, DO> entityNormalisation,
      EntityError entityError,
      NormalisingEntityValidation<PO, DO> entityValidation,
      ReactiveCrudRepository<PO, String> repository) {
    super(entityError, entityValidation, repository);
    this.entityNormalisation = entityNormalisation;
    this.entityValidation = entityValidation;
  }

  public Mono<DO> getDO(String entityId) {
    return get(entityId).flatMap(entityNormalisation::toDO);
  }

  public Flux<DO> getDOs(Collection<String> ids) {
    return get(ids).flatMap(entityNormalisation::toDO);
  }

  public Flux<DO> getAllDOs() {
    return getAll().flatMap(entityNormalisation::toDO);
  }

  public Mono<DO> save(DO domainObject) {
    return entityValidation.validate(domainObject)
        .map(entityNormalisation::toPO)
        .flatMap(repository::save).thenReturn(domainObject);
  }

  public Mono<DO> upsert(PO persistenceObject) {
    return entityNormalisation.toDO(persistenceObject).flatMap(this::save);
  }
}
