package com.arrr.piratery.treasure.ports.domain;

import com.arrr.piratery.commons.events.crew.CrewUpdated;
import com.arrr.piratery.treasure.services.domain.AvailableCrewService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CrewHandler {

  private final AvailableCrewService availableCrewService;

  @EventListener
  void handleTreasureUpdated(CrewUpdated event) {
    availableCrewService.save(event)
        .subscribe(availableTreasure -> System.out.println("Handled crew updated event."));
  }

}