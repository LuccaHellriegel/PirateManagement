package com.arrr.piratery.treasure.domain;

import com.arrr.piratery.commons.base.types.Entity;
import com.arrr.piratery.commons.events.treasure.TreasureUpdated;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Treasure implements Entity {

  private String id;
  private String name;
  private String owner;
  private Position position;
  private BigDecimal size;
  private Set<AvailableCrew> assignedCrews;

  public Treasure assignCrew(AvailableCrew availableCrew) {
    var newAssignedCrews = new HashSet<>(assignedCrews);
    newAssignedCrews.add(availableCrew);
    this.assignedCrews = newAssignedCrews;
    return this;
  }

  public TreasureUpdated toEvent() {
    return new TreasureUpdated(id, name, size);
  }
}
