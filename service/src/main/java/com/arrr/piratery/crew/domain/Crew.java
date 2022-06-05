package com.arrr.piratery.crew.domain;

import com.arrr.piratery.commons.base.exceptions.InvalidEntityException;
import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.treasure.domain.Treasure;
import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Crew implements DomainObject {

  private String id;
  private String name;
  private int capacity;
  private Set<Treasure> assignedTreasures;

  public Crew validate() {
    var treasureSize = assignedTreasures.stream().map(Treasure::getSize).reduce(
        BigDecimal.valueOf(0), BigDecimal::add);
    if (treasureSize.compareTo(BigDecimal.valueOf(capacity)) > 0) {
      throw new InvalidEntityException(this.getClass(),
          "Crew " + id + "'s capacity was exceeded by the assigned treasures.");
    }

    return this;
  }
}
