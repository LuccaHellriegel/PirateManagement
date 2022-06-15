package com.arrr.piratery.crew;

import com.arrr.piratery.treasure.Treasure;
import com.arrr.piratery.commons.Entity;
import com.arrr.piratery.commons.exceptions.InvalidEntityException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Data
@Document("crews")
public class Crew implements Entity {

  @NotBlank
  private String id;
  @NotBlank
  private String name;
  @Min(0)
  private BigDecimal capacity;
  @Min(0)
  private BigDecimal usedCapacity;
  @NotNull
  private Set<String> assignedTreasures;

  private BigDecimal calculateNewUsedCapacity(Treasure toBeAdded) {
    var assignedTreasuresSize = usedCapacity.add(toBeAdded.getSize());
    if (assignedTreasuresSize.compareTo(capacity) > 0) {
      throw new InvalidEntityException(this.getClass(),
          "Crew " + id + "'s capacity " + capacity + " was exceeded by the assigned treasure size "
              + assignedTreasuresSize + ".");
    }

    return assignedTreasuresSize;
  }

  public Crew assignTreasure(Treasure treasure) {
    if (assignedTreasures.contains(treasure.getId())) {
      return this;
    }

    var newTreasures = new HashSet<>(assignedTreasures);
    newTreasures.add(treasure.getId());
    setAssignedTreasures(newTreasures);
    usedCapacity = calculateNewUsedCapacity(treasure);
    return this;
  }
}
