package com.arrr.piratery.services.domain.crew;

import com.arrr.piratery.commons.base.normalisation.BaseEntityNormalisation;
import com.arrr.piratery.domain.Crew;
import com.arrr.piratery.ports.domain.crew.CrewMapper;
import com.arrr.piratery.ports.domain.crew.CrewPO;
import com.arrr.piratery.services.domain.treasure.TreasureService;
import java.util.HashSet;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CrewNormalisation extends BaseEntityNormalisation<CrewPO, Crew> {

  private final TreasureService treasureService;

  public CrewNormalisation(
      CrewMapper mapper, TreasureService treasureService) {
    super(mapper);
    this.treasureService = treasureService;
  }

  @Override
  public Mono<Crew> toDO(CrewPO po) {
    return treasureService.get(po.getAssignedTreasures()).collectList().map(treasures -> {
      var partialCrew = mapper.partialToDO(po);
      partialCrew.setAssignedTreasures(new HashSet<>(treasures));
      return partialCrew;
    });
  }

}
