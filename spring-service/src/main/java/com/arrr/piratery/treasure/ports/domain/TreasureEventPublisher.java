package com.arrr.piratery.treasure.ports.domain;

import com.arrr.piratery.commons.events.crew.TreasureAssigned;
import com.arrr.piratery.treasure.domain.Treasure;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TreasureEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  public Treasure treasureUpdated(Treasure updatedTreasure) {
    System.out.println("Publishing treasure updated event.");
    applicationEventPublisher.publishEvent(updatedTreasure.toEvent());
    return updatedTreasure;
  }

  public void treasureAssigned(String crewId, String treasureId) {
    System.out.println("Publishing pirate has assigned treasure event.");
    applicationEventPublisher.publishEvent(new TreasureAssigned(crewId, treasureId));
  }


}

