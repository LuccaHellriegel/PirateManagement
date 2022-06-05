package com.arrr.piratery.ports.domain.crew;

import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class CrewPO implements PersistenceObject {

  @Id
  private String id;
  private String name;
  private int capacity;
  private Set<String> assignedTreasures;

}
