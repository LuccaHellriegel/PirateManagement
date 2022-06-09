package com.arrr.piratery.commons.services.domain;

import com.arrr.piratery.commons.base.services.norm.GetDOMixin;
import com.arrr.piratery.commons.base.services.norm.SaveDOMixin;
import com.arrr.piratery.commons.base.services.norm.ValidateDOMixin;
import com.arrr.piratery.commons.ports.domain.CrewMapper;
import com.arrr.piratery.commons.ports.domain.CrewPO;
import com.arrr.piratery.commons.ports.domain.CrewRepository;
import com.arrr.piratery.crew.domain.Crew;
import java.util.List;
import java.util.function.Function;
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
  public List<Function<Crew, Mono<Crew>>> getValidationMethods() {
    return List.of(Crew::validate);
  }

}
