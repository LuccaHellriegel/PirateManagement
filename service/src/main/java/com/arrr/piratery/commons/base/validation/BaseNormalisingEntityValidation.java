package com.arrr.piratery.commons.base.validation;

import com.arrr.piratery.commons.base.error.EntityError;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * For all validations by default, this service converts DOs to POs and delegates to the normal base
 * service.
 */
public class BaseNormalisingEntityValidation<PO extends PersistenceObject, DO extends DomainObject> extends
    BaseEntityValidation<PO> implements NormalisingEntityValidation<PO, DO> {

  public BaseNormalisingEntityValidation(
      EntityError entityError,
      ReactiveCrudRepository<PO, String> repository) {
    super(entityError, repository);
  }

  /**
   * The default validation always returns true.
   */
  public Mono<Boolean> isUnique(DO domainObject) {
    return Mono.just(true);
  }

  public Mono<DO> validateUniqueness(DO domainObject) {
    return isUnique(domainObject).map(isUnique -> {
      if (!isUnique) {
        throw entityError.duplicate();
      }
      return domainObject;
    });
  }

  /**
   * The default validation always returns true.
   */
  public Mono<Boolean> hasValidProperties(DO domainObject) {
    return Mono.just(true);
  }

  public Mono<DO> validateEntityProps(DO domainObject) {
    return hasValidProperties(domainObject).map(isValid -> {
      if (!isValid) {
        throw entityError.invalid();
      }
      return domainObject;
    });
  }

  @Override
  public Mono<DO> validate(DO domainObject) {
    return validateEntityProps(domainObject).flatMap(this::validateUniqueness);
  }
}
