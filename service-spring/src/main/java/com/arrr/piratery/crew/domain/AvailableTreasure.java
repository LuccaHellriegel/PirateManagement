package com.arrr.piratery.crew.domain;

import com.arrr.piratery.commons.base.types.Entity;
import java.math.BigDecimal;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Document("available_treasures")
public class AvailableTreasure implements Entity {

  String id;
  String name;
  BigDecimal size;
}
