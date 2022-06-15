package com.arrr.piratery.treasure.ports.domain;

import com.arrr.piratery.commons.base.types.Entity;
import com.arrr.piratery.treasure.domain.Position;
import java.math.BigDecimal;
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
public class TreasurePO implements Entity {

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
  @NotNull
  private Set<String> assignedCrews;
}
