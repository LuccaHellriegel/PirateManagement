package com.arrr.piratery.services.domain.treasure;

import com.arrr.piratery.commons.base.error.BaseEntityError;
import com.arrr.piratery.domain.Treasure;
import org.springframework.stereotype.Component;

@Component
public class TreasureError extends BaseEntityError {

  public TreasureError() {
    super(Treasure.class.getSimpleName());
  }
}
