package com.arrr.piratery.commons.base.mixins.norm;

import com.arrr.piratery.commons.base.mixins.core.Normalisation;
import com.arrr.piratery.commons.base.mixins.entity.GetEntityMixin;
import com.arrr.piratery.commons.base.types.Entity;
import java.util.Collection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GetDOMixin<PO extends Entity, DO extends Entity>
    extends GetEntityMixin<PO>, Normalisation<PO, DO> {

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
