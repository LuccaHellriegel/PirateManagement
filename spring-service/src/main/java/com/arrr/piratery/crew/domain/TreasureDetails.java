package com.arrr.piratery.crew.domain;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TreasureDetails {

  private String id;
  private String name;
  private BigDecimal size;
}
