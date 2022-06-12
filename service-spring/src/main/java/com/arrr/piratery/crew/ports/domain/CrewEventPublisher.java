package com.arrr.piratery.crew.ports.domain;


import com.arrr.piratery.crew.domain.Crew;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CrewEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  public Crew crewUpdated(Crew updatedCrew) {
    System.out.println("Publishing crew updated event.");
    applicationEventPublisher.publishEvent(updatedCrew.toEvent());
    return updatedCrew;
  }

}
