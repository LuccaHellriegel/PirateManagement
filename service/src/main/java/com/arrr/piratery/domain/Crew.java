package com.arrr.piratery.domain;

import com.arrr.piratery.commons.base.types.PersistenceObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
public class Crew implements PersistenceObject {

  @Id
  private String id;
  private String name;
  private int capacity;
}
