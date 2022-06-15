package com.arrr.piratery.treasure.domain;

import com.arrr.piratery.commons.base.types.Entity;
import com.arrr.piratery.commons.events.treasure.TreasureUpdated;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Treasure implements Entity {

  @NotBlank
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String owner;
  @NotNull
  private Position position;
  @Min(0)
  private BigDecimal size;
  @NotNull
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
