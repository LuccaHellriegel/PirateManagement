package com.arrr.piratery.crew.services.domain;

import com.arrr.piratery.commons.base.mixins.norm.GetDOMixin;
import com.arrr.piratery.commons.base.mixins.norm.SaveDOMixin;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.crew.ports.domain.CrewEventPublisher;
import com.arrr.piratery.crew.ports.domain.CrewMapper;
import com.arrr.piratery.crew.ports.domain.CrewPO;
import com.arrr.piratery.crew.ports.domain.CrewRepository;
import java.util.HashSet;
import lombok.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Value
public class CrewService implements
    GetDOMixin<CrewPO, Crew>,
    SaveDOMixin<CrewPO, Crew> {

  CrewMapper mapper;
  CrewRepository repository;
  AvailableTreasureService availableTreasureService;
  CrewEventPublisher crewEventPublisher;

  @Override
  public CrewPO toPO(Crew domainObject) {
    return mapper.toPO(domainObject);
  }

  @Override
  public Mono<Crew> toDO(CrewPO po) {
    var partial = mapper.partialToDO(po);
    return availableTreasureService.get(po.getAssignedTreasures()).collectList().map(HashSet::new)
        .map(partial::setAssignedTreasures);
  }

  @Override
  public Class<CrewPO> getEntityClass() {
    return CrewPO.class;
  }

  public Mono<Crew> create(CrewPO persistenceObject) {
    persistenceObject.setId(null);
    persistenceObject.setAssignedTreasures(new HashSet<>());
    return toDO(persistenceObject)
        .flatMap(this::insert)
        .map(crewEventPublisher::crewUpdated);
  }

  public Mono<Crew> assign(String crewId, String treasureId) {
    return Mono.zip(getDO(crewId), availableTreasureService.get(treasureId))
        .flatMap(t -> {
          var crew = t.getT1();
          var treasure = t.getT2();

          crew.assignTreasure(treasure);
          return save(crew);
        });
  }
}
