package com.arrr.piratery.treasure.domain;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.ports.domain.Position;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Treasure implements DomainObject {

  private String id;
  private String name;
  private String owner;
  private Position position;
  private BigDecimal size;
  private Set<CrewDetails> assignedCrews;

  public Treasure assignCrew(CrewDetails crewDetails) {
    var newAssignedCrews = new HashSet<>(assignedCrews);
    newAssignedCrews.add(crewDetails);
    this.assignedCrews = newAssignedCrews;
    return this;
  }
}
