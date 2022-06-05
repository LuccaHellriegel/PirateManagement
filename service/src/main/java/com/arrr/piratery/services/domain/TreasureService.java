package com.arrr.piratery.services.domain;

import com.arrr.piratery.commons.base.EntityRepository;
import com.arrr.piratery.domain.QTreasure;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.ports.domain.TreasureRepository;
import com.querydsl.core.BooleanBuilder;
import java.util.Optional;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Getter
public class TreasureService extends EntityRepository<Treasure> {

  private final TreasureRepository repository;
  private static final float DEFAULT_RADIUS = 10F;

  public TreasureService(TreasureRepository repository) {
    super(Treasure.class, repository);
    this.repository = repository;
  }

  public static BooleanBuilder inRadiusPredicate(float x, float y, float radius) {
    var query = QTreasure.treasure.position;
    return new BooleanBuilder()
        .and(query.x.between(x - radius, x + radius))
        .and(query.y.between(y - radius, y + radius));
  }

  public Flux<Treasure> getTreasuresInRadius(float x, float y, Optional<Float> radiusOptional) {
    float radius = radiusOptional.orElse(DEFAULT_RADIUS);
    return repository.findAll(inRadiusPredicate(x, y, radius));
  }
}
