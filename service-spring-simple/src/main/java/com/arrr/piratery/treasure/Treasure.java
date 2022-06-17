package com.arrr.piratery.treasure;

import com.arrr.piratery.commons.Entity;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document("treasures")
public class Treasure implements Entity {

  @Id
  private String id;
  @NotBlank
  private String name;
  @NotBlank
  private String owner;
  @NotNull
  private Position position;
  @Min(0)
  private BigDecimal size;
  private Set<String> assignedCrews;

  public Treasure assignCrew(String crewId) {
    var newAssignedCrews = new HashSet<>(assignedCrews);
    newAssignedCrews.add(crewId);
    this.assignedCrews = newAssignedCrews;
    return this;
  }

  @Data
  @AllArgsConstructor
  public static class Position {

    private float x;
    private float y;

  }
}
