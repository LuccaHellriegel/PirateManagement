package com.arrr.piratery.commons.services.domain;

import com.arrr.piratery.commons.base.services.Normalisation;
import com.arrr.piratery.commons.ports.domain.CrewPO;
import com.arrr.piratery.commons.services.domain.BaseTreasureService;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.commons.ports.domain.CrewMapper;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Getter
public class CrewNormalisation extends Normalisation<CrewPO, Crew> {

  private final BaseTreasureService treasureService;
  private final CrewMapper mapper;

  public CrewNormalisation(
      CrewMapper crewMapper, BaseTreasureService treasureService) {
    super(crewMapper);
    this.mapper = crewMapper;
    this.treasureService = treasureService;
  }

  @Override
  public Mono<Crew> toDO(CrewPO po) {
    return treasureService.getDOs(po.getAssignedTreasures()).collectList().map(treasures -> {
      var partialCrew = mapper.partialToDO(po);
      partialCrew.setAssignedTreasures(
          treasures.stream().map(mapper::toDetails).collect(Collectors.toSet()));
      return partialCrew;
    });
  }
}
