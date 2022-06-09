package com.arrr.piratery.crew.domain;

import com.arrr.piratery.commons.base.exceptions.InvalidEntityException;
import com.arrr.piratery.commons.base.types.DomainObject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Mono;

@Data
@AllArgsConstructor
public class Crew implements DomainObject {

  private String id;
  private String name;
  private int capacity;
  private Set<TreasureDetails> assignedTreasures;

  public Mono<Crew> validate() {
    var treasureSize = assignedTreasures.stream().map(TreasureDetails::getSize).reduce(
        BigDecimal.valueOf(0), BigDecimal::add);
    if (treasureSize.compareTo(BigDecimal.valueOf(capacity)) > 0) {
      throw new InvalidEntityException(this.getClass(),
          "Crew " + id + "'s capacity was exceeded by the assigned treasures.");
    }

    return Mono.just(this);
  }

  public Crew assignTreasure(TreasureDetails treasureDetails) {
    //duplicate assignments are just swallowed
    var newTreasures = new HashSet<>(getAssignedTreasures());
    newTreasures.add(treasureDetails);
    setAssignedTreasures(newTreasures);
    return this;
  }

}
