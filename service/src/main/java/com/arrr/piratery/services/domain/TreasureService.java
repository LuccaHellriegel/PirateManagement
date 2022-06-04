package com.arrr.piratery.services.domain;

import com.arrr.piratery.commons.base.EntityRepository;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.ports.domain.TreasureRepository;
import org.springframework.stereotype.Service;

@Service
public class TreasureService extends EntityRepository<Treasure> {

  public TreasureService(TreasureRepository repository) {
    super(Treasure.class, repository);
  }
}
