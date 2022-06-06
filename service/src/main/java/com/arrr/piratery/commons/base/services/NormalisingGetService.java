package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.util.Collection;
import lombok.Getter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Getter
public abstract class NormalisingGetService<PO extends PersistenceObject, DO extends DomainObject> extends
    GetService<PO> {

  protected final Normalisation<PO, DO> normalisation;

  public NormalisingGetService(
      Class<PO> entityClass,
      ReactiveCrudRepository<PO, String> repository,
      Normalisation<PO, DO> normalisation) {
    super(entityClass, repository);
    this.normalisation = normalisation;
  }

  public Mono<DO> getDO(String entityId) {
    return get(entityId).flatMap(normalisation::toDO);
  }

  public Flux<DO> getDOs(Collection<String> ids) {
    return get(ids).flatMap(normalisation::toDO);
  }

  public Flux<DO> getAllDOs() {
    return getAll().flatMap(normalisation::toDO);
  }

}
