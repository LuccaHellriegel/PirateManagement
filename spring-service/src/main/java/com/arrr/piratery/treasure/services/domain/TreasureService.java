package com.arrr.piratery.treasure.services.domain;

import com.arrr.piratery.commons.base.mixins.norm.GetDOMixin;
import com.arrr.piratery.commons.base.mixins.norm.SaveDOMixin;
import com.arrr.piratery.treasure.domain.Treasure;
import com.arrr.piratery.treasure.ports.domain.QTreasurePO;
import com.arrr.piratery.treasure.ports.domain.TreasureEventPublisher;
import com.arrr.piratery.treasure.ports.domain.TreasureMapper;
import com.arrr.piratery.treasure.ports.domain.TreasurePO;
import com.arrr.piratery.treasure.ports.domain.TreasureRepository;
import com.querydsl.core.BooleanBuilder;
import java.util.HashSet;
import java.util.Optional;
import lombok.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Value
public class TreasureService implements
    GetDOMixin<TreasurePO, Treasure>,
    SaveDOMixin<TreasurePO, Treasure> {

  TreasureRepository repository;
  TreasureMapper mapper;
  AvailableCrewService availableCrewService;
  TreasureEventPublisher treasureEventPublisher;

  private static final float DEFAULT_RADIUS = 10F;

  public Mono<Treasure> create(TreasurePO persistenceObject) {
    persistenceObject.setId(null);
    persistenceObject.setAssignedCrews(new HashSet<>());
    return toDO(persistenceObject)
        .flatMap(this::insert)
        .map(treasureEventPublisher::treasureUpdated);
  }

  public static BooleanBuilder inRadiusPredicate(float x, float y, float radius) {
    var query = QTreasurePO.treasurePO.position;
    return new BooleanBuilder()
        .and(query.x.between(x - radius, x + radius))
        .and(query.y.between(y - radius, y + radius));
  }

  public Flux<Treasure> getTreasuresInRadius(float x, float y, Optional<Float> radiusOptional) {
    float radius = radiusOptional.orElse(DEFAULT_RADIUS);
    return repository.findAll(inRadiusPredicate(x, y, radius)).flatMap(this::toDO);
  }


  public Mono<Treasure> assign(String crewId, String treasureId) {
    return Mono.zip(getDO(treasureId), availableCrewService.get(crewId))
        .flatMap(t -> {
          var treasure = t.getT1();
          var crew = t.getT2();

          treasure.assignCrew(crew);
          return save(treasure)
              .doOnSuccess(ignored -> treasureEventPublisher.treasureAssigned(crewId, treasureId));
        });
  }

  @Override
  public Class<TreasurePO> getEntityClass() {
    return TreasurePO.class;
  }

  @Override
  public TreasurePO toPO(Treasure domainObject) {
    return mapper.toPO(domainObject);
  }

  @Override
  public Mono<Treasure> toDO(TreasurePO persistenceObject) {
    var partial = mapper.partialToDO(persistenceObject);
    return availableCrewService
        .get(persistenceObject.getAssignedCrews()).collectList()
        .map(HashSet::new).map(partial::setAssignedCrews);
  }

}
