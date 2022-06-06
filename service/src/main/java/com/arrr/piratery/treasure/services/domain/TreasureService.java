package com.arrr.piratery.treasure.services.domain;

import com.arrr.piratery.commons.ports.domain.QTreasurePO;
import com.arrr.piratery.commons.ports.domain.TreasurePO;
import com.arrr.piratery.commons.ports.domain.TreasureRepository;
import com.arrr.piratery.commons.services.domain.BaseTreasureService;
import com.arrr.piratery.commons.services.domain.TreasureNormalisation;
import com.arrr.piratery.treasure.domain.Treasure;
import com.querydsl.core.BooleanBuilder;
import java.util.Optional;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Getter
public class TreasureService extends BaseTreasureService {

  private final TreasureRepository repository;
  private static final float DEFAULT_RADIUS = 10F;

  public TreasureService(TreasureRepository repository,
      TreasureNormalisation normalisation) {
    super(repository, normalisation);
    this.repository = repository;
  }


  public Mono<Treasure> create(TreasurePO persistenceObject) {
    persistenceObject.setId(null);
    return normalisation.toDO(persistenceObject).flatMap(this::validate)
        .flatMap(this::validatedSave);
  }

  public static BooleanBuilder inRadiusPredicate(float x, float y, float radius) {
    var query = QTreasurePO.treasurePO.position;
    return new BooleanBuilder()
        .and(query.x.between(x - radius, x + radius))
        .and(query.y.between(y - radius, y + radius));
  }

  public Flux<Treasure> getTreasuresInRadius(float x, float y, Optional<Float> radiusOptional) {
    float radius = radiusOptional.orElse(DEFAULT_RADIUS);
    return repository.findAll(inRadiusPredicate(x, y, radius)).flatMap(normalisation::toDO);
  }

}
