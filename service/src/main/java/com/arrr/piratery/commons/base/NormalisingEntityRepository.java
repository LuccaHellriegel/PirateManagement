package com.arrr.piratery.commons.base;

import com.arrr.piratery.commons.base.error.BaseEntityError;
import com.arrr.piratery.commons.base.error.NormalisingEntityError;
import com.arrr.piratery.commons.base.normalisation.BaseEntityNormalisation;
import com.arrr.piratery.commons.base.normalisation.EntityMapper;
import com.arrr.piratery.commons.base.normalisation.EntityNormalisation;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import com.arrr.piratery.commons.base.validation.BaseEntityValidation;
import com.arrr.piratery.commons.base.validation.EntityValidation;
import java.util.Collection;
import lombok.Getter;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * For all validations by default, this service converts DOs to POs and delegates to the normal base
 * service.
 */
@Getter
public class NormalisingEntityRepository<PO extends PersistenceObject, DO extends DomainObject> extends
    EntityRepository<PO> {

  protected final EntityMapper<DO, PO> mapper;
  protected final EntityNormalisation<PO, DO> entityNormalisation;

  public NormalisingEntityRepository(
      EntityNormalisation<PO, DO> entityNormalisation,
      NormalisingEntityError<PO, DO> entityError,
      EntityValidation<PO> entityValidation,
      ReactiveCrudRepository<PO, String> repository,
      EntityMapper<DO, PO> mapper) {
    super(entityError, entityValidation, repository);
    this.mapper = mapper;
    this.entityNormalisation = entityNormalisation;
  }

  public NormalisingEntityRepository(Class<PO> entityClass,
      ReactiveCrudRepository<PO, String> repository, EntityMapper<DO, PO> mapper) {
    super(entityClass, repository);
    this.mapper = mapper;
    this.entityNormalisation = new BaseEntityNormalisation<>(mapper);
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
    return save(entityNormalisation.toPO(domainObject)).thenReturn(domainObject);
  }

}
