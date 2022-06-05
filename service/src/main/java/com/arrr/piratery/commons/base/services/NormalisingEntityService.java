package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.crew.ports.domain.CrewPO;
import java.util.Collection;
import lombok.Getter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public abstract class NormalisingEntityService<PO extends PersistenceObject, DO extends DomainObject> extends
    EntityService<PO> {

  protected final EntityMapper<PO, DO> mapper;

  public NormalisingEntityService(
      Class<PO> entityClass,
      ReactiveCrudRepository<PO, String> repository,
      EntityMapper<PO, DO> mapper) {
    super(entityClass, repository);
    this.mapper = mapper;
  }

  public PO toPO(DO domainObject) {
    return mapper.toPO(domainObject);
  }

  public abstract Mono<DO> toDO(PO persistenceObject);

  public Mono<DO> create(PO persistenceObject) {
    persistenceObject.setId(null);
    return toDO(persistenceObject).flatMap(this::validate).flatMap(this::save);
  }

  public Mono<DO> getDO(String entityId) {
    return get(entityId).flatMap(this::toDO);
  }

  public Flux<DO> getDOs(Collection<String> ids) {
    return get(ids).flatMap(this::toDO);
  }

  public Flux<DO> getAllDOs() {
    return getAll().flatMap(this::toDO);
  }

  public abstract Mono<DO> validate(DO domainObject);

  /**
   * This method will probably not be used. This is just to make sure we always use the DO-validate
   * by default. Using the original PO-validate by accident might be catastrophic.
   */
  @Override
  public Mono<PO> validate(PO persistenceObject) {
    return toDO(persistenceObject).flatMap(this::validate).thenReturn(persistenceObject);
  }

  public Mono<DO> save(DO domainObject) {
    return validate(domainObject)
        .map(this::toPO)
        .flatMap(repository::save)
        .thenReturn(domainObject);
  }

}
