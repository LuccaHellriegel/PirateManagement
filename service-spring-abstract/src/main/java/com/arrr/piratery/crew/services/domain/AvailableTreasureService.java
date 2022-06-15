package com.arrr.piratery.crew.services.domain;

import com.arrr.piratery.commons.base.mixins.entity.GetEntityMixin;
import com.arrr.piratery.commons.events.treasure.TreasureUpdated;
import com.arrr.piratery.crew.domain.AvailableTreasure;
import com.arrr.piratery.crew.ports.domain.AvailableTreasureRepository;
import com.arrr.piratery.crew.ports.domain.CrewMapper;
import lombok.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Value
public class AvailableTreasureService implements GetEntityMixin<AvailableTreasure> {

  AvailableTreasureRepository repository;
  //TODO: split up the Event mapper
  CrewMapper mapper;

  @Override
  public Class<AvailableTreasure> getEntityClass() {
    return AvailableTreasure.class;
  }

  public Mono<AvailableTreasure> save(TreasureUpdated availableTreasure) {
    return repository.save(mapper.toAvailableTreasure(availableTreasure));
  }
}
