package com.arrr.piratery.commons.services.domain;

import com.arrr.piratery.commons.base.services.Normalisation;
import com.arrr.piratery.commons.ports.domain.TreasurePO;
import com.arrr.piratery.treasure.domain.Treasure;
import com.arrr.piratery.commons.ports.domain.TreasureMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Getter
public class TreasureNormalisation extends Normalisation<TreasurePO, Treasure> {

  private final TreasureMapper mapper;

  @Override
  public Mono<Treasure> toDO(TreasurePO persistenceObject) {
    return null;
  }

  public TreasureNormalisation(
      TreasureMapper mapper) {
    super(mapper);
    this.mapper = mapper;
  }
}
