package com.arrr.piratery.commons.services.domain;

import com.arrr.piratery.commons.base.services.GetDOMixin;
import com.arrr.piratery.commons.base.services.SaveDOMixin;
import com.arrr.piratery.commons.base.services.ValidateDOMixin;
import com.arrr.piratery.commons.ports.domain.CrewMapper;
import com.arrr.piratery.commons.ports.domain.CrewPO;
import com.arrr.piratery.commons.ports.domain.CrewRepository;
import com.arrr.piratery.crew.domain.Crew;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Getter
@AllArgsConstructor
public class BaseCrewService implements
    GetDOMixin<CrewPO, Crew>,
    SaveDOMixin<CrewPO, Crew>,
    ValidateDOMixin<CrewPO, Crew> {

  protected final CrewMapper mapper;
  protected final BaseTreasureService treasureService;
  protected final CrewRepository repository;


  @Override
  public Mono<Crew> toDO(CrewPO po) {
    return treasureService.getDOs(po.getAssignedTreasures()).collectList().map(treasures -> {
      var partialCrew = mapper.partialToDO(po);
      partialCrew.setAssignedTreasures(
          treasures.stream().map(mapper::toDetails).collect(Collectors.toSet()));
      return partialCrew;
    });
  }

  @Override
  public Class<CrewPO> getEntityClass() {
    return CrewPO.class;
  }

  @Override
  public Mono<Crew> validate(Crew domainObject) {
    return Mono.just(domainObject.validate());
  }
}
