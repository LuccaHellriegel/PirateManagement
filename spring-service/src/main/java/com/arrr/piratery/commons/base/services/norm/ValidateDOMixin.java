package com.arrr.piratery.commons.base.services.norm;

import com.arrr.piratery.commons.base.services.core.GetRepository;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.util.List;
import java.util.function.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ValidateDOMixin<PO extends PersistenceObject, DO extends DomainObject>
    extends NormalisationMixin<PO, DO>, GetRepository<PO> {

  List<Function<DO, Mono<DO>>> getValidationMethods();

  default Mono<DO> validate(DO domainObject) {
    return Flux.fromIterable(getValidationMethods()).flatMap(method -> method.apply(domainObject))
        .then(Mono.just(domainObject));
  }

  default Mono<DO> validatedSave(DO domainObject) {
    return validate(domainObject)
        .map(this::toPO)
        .flatMap(getRepository()::save)
        .thenReturn(domainObject);
  }


}
