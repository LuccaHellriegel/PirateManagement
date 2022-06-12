package com.arrr.piratery.crew.domain;

import com.arrr.piratery.commons.base.exceptions.InvalidEntityException;
import com.arrr.piratery.commons.base.types.Entity;
import com.arrr.piratery.commons.events.crew.CrewUpdated;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Crew implements Entity {

  private String id;
  private String name;
  private int capacity;
  private Set<AvailableTreasure> assignedTreasures;

  private Crew validate() {
    var treasureSize = assignedTreasures.stream().map(AvailableTreasure::getSize).reduce(
        BigDecimal.valueOf(0), BigDecimal::add);
    if (treasureSize.compareTo(BigDecimal.valueOf(capacity)) > 0) {
      throw new InvalidEntityException(this.getClass(),
          "Crew " + id + "'s capacity " + capacity + " was exceeded by the assigned treasure size "
              + treasureSize + ".");
    }

    return this;
  }

  //TODO: this does not make sense with the treasure-centric assignment logic
  //would need a second "appliedTreasures"
  //but CAN apply to more than you can accept!!!
  public Crew assignTreasure(AvailableTreasure availableTreasure) {
    //duplicate assignments are just swallowed
    var newTreasures = new HashSet<>(getAssignedTreasures());
    newTreasures.add(availableTreasure);
    setAssignedTreasures(newTreasures);
    return validate();
  }

  public CrewUpdated toEvent() {
    return new CrewUpdated(id, name);
  }

}
