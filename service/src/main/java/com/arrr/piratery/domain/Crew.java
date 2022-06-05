package com.arrr.piratery.domain;

import com.arrr.piratery.commons.base.error.EntityError;
import com.arrr.piratery.commons.base.types.DomainObject;
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

  public Crew validate(EntityError entityError) {
    var treasureSize = assignedTreasures.stream().map(Treasure::getSize).reduce(
        BigDecimal.valueOf(0), BigDecimal::add);
    if (treasureSize.compareTo(BigDecimal.valueOf(capacity)) > 0) {
      throw entityError.invalid("Crew " + id + "'s capacity was exceeded by assigned treasures.");
    }

    return this;
  }
}
