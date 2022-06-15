package com.arrr.piratery.treasure;

import com.arrr.piratery.QTreasure;
import com.arrr.piratery.commons.exceptions.EntityNotFoundException;
import com.arrr.piratery.crew.CrewService;
import com.querydsl.core.BooleanBuilder;
import java.util.HashSet;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TreasureService {

  TreasureRepository repository;
  CrewService crewService;

  private static final float DEFAULT_RADIUS = 10F;

  public Mono<Treasure> create(Treasure persistenceObject) {
    persistenceObject.setId(null);
    persistenceObject.setAssignedCrews(new HashSet<>());
    return repository.insert(persistenceObject);
  }

  //alternatively if you don't want to use QueryDsl, just get all and then filter out, but less performant
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


  public Mono<Treasure> get(String treasureId) {
    return repository.findById(treasureId)
        .switchIfEmpty(Mono.error(new EntityNotFoundException(Treasure.class, treasureId)));
  }

  public Flux<Treasure> getAll() {
    return repository.findAll();
  }

  public Mono<Treasure> assign(String crewId, String treasureId) {
    return Mono.zip(crewService.get(crewId), get(treasureId)).flatMap(t -> {
      var crew = t.getT1();
      var treasure = t.getT2();
      treasure.assignCrew(crewId);
      crew.assignTreasure(treasure);

      return crewService.getRepository().save(crew).then(repository.save(treasure)).doOnSuccess(
          ignored -> System.out.println(
              "Assigned crew " + crewId + " to treasure " + treasureId + "."));
    });

  }

}
