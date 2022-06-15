package com.arrr.piratery.commons.base.mixins.norm;

import com.arrr.piratery.commons.base.mixins.core.GetRepository;
import com.arrr.piratery.commons.base.mixins.core.Normalisation;
import com.arrr.piratery.commons.base.types.Entity;
import java.util.List;
import java.util.function.Function;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ValidateDOMixin<PO extends Entity, DO extends Entity>
    extends Normalisation<PO, DO>, GetRepository<PO> {

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
