package com.arrr.piratery.treasure.domain;

import com.arrr.piratery.commons.base.types.Entity;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document("available_crews")
public class AvailableCrew implements Entity {

  String id;
  String name;
}
