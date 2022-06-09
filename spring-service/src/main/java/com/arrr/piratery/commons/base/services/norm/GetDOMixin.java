package com.arrr.piratery.commons.base.services.norm;

import com.arrr.piratery.commons.base.services.entity.GetEntityMixin;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.util.Collection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetDOMixin<PO extends PersistenceObject, DO extends DomainObject>
    extends GetEntityMixin<PO>, NormalisationMixin<PO, DO> {

  default Mono<DO> getDO(String entityId) {
    return get(entityId).flatMap(this::toDO);
  }

  default Flux<DO> getDOs(Collection<String> ids) {
    return get(ids).flatMap(this::toDO);
  }

  default Flux<DO> getAllDOs() {
    return getAll().flatMap(this::toDO);
  }
}
