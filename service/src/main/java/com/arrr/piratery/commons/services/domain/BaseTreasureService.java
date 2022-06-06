package com.arrr.piratery.commons.services.domain;

import com.arrr.piratery.commons.base.services.NormalisingEntityService;
import com.arrr.piratery.commons.ports.domain.TreasurePO;
import com.arrr.piratery.commons.ports.domain.TreasureRepository;
import com.arrr.piratery.treasure.domain.Treasure;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Getter
public class BaseTreasureService extends NormalisingEntityService<TreasurePO, Treasure> {

  protected final TreasureNormalisation normalisation;

  public BaseTreasureService(TreasureRepository repository,
      TreasureNormalisation normalisation) {
    super(TreasurePO.class, repository, normalisation);
    this.normalisation = normalisation;
  }

  @Override
  public Mono<Treasure> validate(Treasure domainObject) {
    return Mono.just(domainObject);
  }
}