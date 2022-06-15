package com.arrr.piratery.view;

import com.arrr.piratery.crew.CrewService;
import com.arrr.piratery.treasure.TreasureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ViewService {

  private final TreasureService treasureService;
  private final CrewService crewService;

  public Mono<TreasureView> getTreasureView(String treasureId) {
    return treasureService.get(treasureId).flatMap(treasure ->
        Flux.fromIterable(treasure.getAssignedCrews())
            .flatMap(crewService::get)
            .collectList()
            .map(crewList -> new TreasureView(treasure, crewList))
    );
  }

  public Mono<CrewView> getCrewView(String crewId) {
    return crewService.get(crewId).flatMap(crew ->
        Flux.fromIterable(crew.getAssignedTreasures())
            .flatMap(treasureService::get)
            .collectList()
            .map(treasureList -> new CrewView(crew, treasureList))
    );
  }

}
