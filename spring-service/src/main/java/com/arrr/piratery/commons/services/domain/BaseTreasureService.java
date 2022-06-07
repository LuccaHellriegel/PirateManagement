package com.arrr.piratery.commons.services.domain;

import com.arrr.piratery.commons.base.services.GetDOMixin;
import com.arrr.piratery.commons.base.services.SaveDOMixin;
import com.arrr.piratery.commons.ports.domain.TreasureMapper;
import com.arrr.piratery.commons.ports.domain.TreasurePO;
import com.arrr.piratery.commons.ports.domain.TreasureRepository;
import com.arrr.piratery.treasure.domain.Treasure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Getter
@AllArgsConstructor
public class BaseTreasureService implements
    GetDOMixin<TreasurePO, Treasure>,
    SaveDOMixin<TreasurePO, Treasure> {

  protected final TreasureRepository repository;
  protected final TreasureMapper mapper;

  @Override
  public Class<TreasurePO> getEntityClass() {
    return TreasurePO.class;
  }

  //TODO: need lower services so we can get DO
  @Override
  public Mono<Treasure> toDO(TreasurePO persistenceObject) {
    return null;
  }
}