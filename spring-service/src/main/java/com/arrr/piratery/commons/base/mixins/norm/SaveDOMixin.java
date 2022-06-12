package com.arrr.piratery.commons.base.mixins.norm;

import com.arrr.piratery.commons.base.mixins.core.GetRepository;
import com.arrr.piratery.commons.base.mixins.core.Normalisation;
import com.arrr.piratery.commons.base.types.Entity;
import reactor.core.publisher.Mono;

public interface SaveDOMixin<PO extends Entity, DO extends Entity>
    extends GetRepository<PO>, Normalisation<PO, DO> {

  default Mono<DO> save(DO domainObject) {
    return getRepository().save(toPO(domainObject))
        .thenReturn(domainObject);
  }

  default Mono<DO> insert(DO domainObject) {
    return getRepository().save(toPO(domainObject)).flatMap(this::toDO);
  }


}
