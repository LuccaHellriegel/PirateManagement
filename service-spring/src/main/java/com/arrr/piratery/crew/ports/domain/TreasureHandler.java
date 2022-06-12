package com.arrr.piratery.crew.ports.domain;

import com.arrr.piratery.commons.events.crew.TreasureAssigned;
import com.arrr.piratery.commons.events.treasure.TreasureUpdated;
import com.arrr.piratery.crew.services.domain.AvailableTreasureService;
import com.arrr.piratery.crew.services.domain.CrewService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TreasureHandler {

  private final AvailableTreasureService availableTreasureService;
  private final CrewService crewService;

  @EventListener
  void handleTreasureUpdated(TreasureUpdated event) {
    availableTreasureService.save(event)
        .subscribe(availableTreasure -> System.out.println("Handled treasure updated event."));
  }

  @EventListener
  void handleTreasureAssigned(TreasureAssigned event) {
    crewService.assign(event.getCrewId(), event.getTreasureId())
        .subscribe(ignored -> System.out.println("Handled treasure assigned event."));
  }

}
