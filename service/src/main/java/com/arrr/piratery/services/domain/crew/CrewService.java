package com.arrr.piratery.services.domain.crew;

import com.arrr.piratery.commons.base.services.NormalisingEntityService;
import com.arrr.piratery.domain.Crew;
import com.arrr.piratery.ports.domain.crew.CrewPO;
import com.arrr.piratery.ports.domain.crew.CrewRepository;
import com.arrr.piratery.services.domain.treasure.TreasureService;
import java.util.HashSet;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CrewService extends NormalisingEntityService<CrewPO, Crew> {

  private final TreasureService treasureService;


  public CrewService(CrewError crewError, CrewRepository repository, CrewValidation crewValidation,
      CrewNormalisation crewNormalisation, TreasureService treasureService) {
    super(crewNormalisation, crewError, crewValidation, repository);
    this.treasureService = treasureService;
  }

  public Mono<Crew> assignTreasure(String crewId, String treasureId) {
    return Mono.zip(getDO(crewId), treasureService.get(treasureId)).map(t -> {
          var crew = t.getT1();
          var treasure = t.getT2();

          //duplicate assignments are just swallowed
          var newTreasures = new HashSet<>(crew.getAssignedTreasures());
          newTreasures.add(treasure);
          crew.setAssignedTreasures(newTreasures);
          return crew;
        })
        .flatMap(this::save);

  }
}
