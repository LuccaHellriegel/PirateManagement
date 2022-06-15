package com.arrr.piratery.treasure.services.domain;

import com.arrr.piratery.commons.base.mixins.entity.GetEntityMixin;
import com.arrr.piratery.commons.base.mixins.entity.SaveEntityMixin;
import com.arrr.piratery.commons.events.crew.CrewUpdated;
import com.arrr.piratery.treasure.domain.AvailableCrew;
import com.arrr.piratery.treasure.ports.domain.AvailableCrewRepository;
import com.arrr.piratery.treasure.ports.domain.TreasureMapper;
import lombok.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Value
public class AvailableCrewService implements
    GetEntityMixin<AvailableCrew>,
    SaveEntityMixin<AvailableCrew> {

  AvailableCrewRepository repository;
  TreasureMapper mapper;

  @Override
  public Class<AvailableCrew> getEntityClass() {
    return AvailableCrew.class;
  }

  public Mono<AvailableCrew> save(CrewUpdated availableCrew) {
    return repository.save(mapper.toAvailableCrew(availableCrew));
  }

}
