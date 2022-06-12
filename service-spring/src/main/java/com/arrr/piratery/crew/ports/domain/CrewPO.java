package com.arrr.piratery.crew.ports.domain;

import com.arrr.piratery.commons.base.types.Entity;
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
@Document("crews")
public class CrewPO implements Entity {

  @Id
  private String id;
  @NotBlank
  private String name;
  @Min(0)
  private int capacity;
  @NotNull
  private Set<String> assignedTreasures;

}


