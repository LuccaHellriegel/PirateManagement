package com.arrr.piratery.crew.services.domain;

import com.arrr.piratery.commons.base.services.NormalisingEntityService;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.crew.ports.domain.CrewMapper;
import com.arrr.piratery.crew.ports.domain.CrewPO;
import com.arrr.piratery.crew.ports.domain.CrewRepository;
import com.arrr.piratery.treasure.services.domain.TreasureService;
import java.util.HashSet;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CrewService extends NormalisingEntityService<CrewPO, Crew> {

  private final TreasureService treasureService;
  private final CrewMapper mapper;

  public CrewService(CrewRepository repository,
      CrewMapper mapper, TreasureService treasureService) {
    super(CrewPO.class, repository, mapper);
    this.treasureService = treasureService;
    this.mapper = mapper;
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

  @Override
  public Mono<Crew> toDO(CrewPO po) {
    return treasureService.get(po.getAssignedTreasures()).collectList().map(treasures -> {
      var partialCrew = mapper.partialToDO(po);
      partialCrew.setAssignedTreasures(new HashSet<>(treasures));
      return partialCrew;
    });
  }

  @Override
  public Mono<Crew> validate(Crew crew) {
    return Mono.just(crew.validate());
  }

  public Mono<Crew> create(CrewPO crew) {
    return toDO(crew).flatMap(this::validate).flatMap(this::save);
  }
}
