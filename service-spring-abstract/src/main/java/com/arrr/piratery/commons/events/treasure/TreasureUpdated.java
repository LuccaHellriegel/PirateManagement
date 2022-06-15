package com.arrr.piratery.commons.events.treasure;

import java.math.BigDecimal;
import lombok.Value;

@Value
public class TreasureUpdated {

  String id;
  String name;
  BigDecimal size;
}
