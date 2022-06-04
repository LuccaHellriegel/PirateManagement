package com.arrr.piratery.domain;

import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Treasure implements PersistenceObject {

  @Id
  private String id;
  @NotBlank
  private String owner;
  @NotNull
  private Position position;
  @NotNull
  private BigDecimal size;
}
