package com.arrr.piratery.services.domain.treasure;

import com.arrr.piratery.commons.base.validation.BaseEntityValidation;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.ports.domain.TreasureRepository;
import com.arrr.piratery.services.domain.treasure.TreasureError;
import org.springframework.stereotype.Component;

@Component
public class TreasureValidation extends BaseEntityValidation<Treasure> {

  public TreasureValidation(TreasureError treasureError,
      TreasureRepository treasureRepository) {
    super(treasureError, treasureRepository);
  }
}
