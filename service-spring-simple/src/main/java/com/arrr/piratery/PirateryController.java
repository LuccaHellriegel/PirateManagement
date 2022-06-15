package com.arrr.piratery;

import com.arrr.piratery.commons.Entity;
import com.arrr.piratery.crew.Crew;
import com.arrr.piratery.crew.CrewService;
import com.arrr.piratery.treasure.Treasure;
import com.arrr.piratery.treasure.TreasureService;
import com.arrr.piratery.view.CrewView;
import com.arrr.piratery.view.TreasureView;
import com.arrr.piratery.view.ViewService;
import java.net.URI;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping(PirateryController.BASE_PATH)
public class PirateryController {

  public static final String BASE_PATH = "/api/v1/";
  //just for DEMO purposes, you should absolutely split up your Controllers
  public static final String TREASURE_CONTEXT = "treasures";
  public static final String CREW_CONTEXT = "crews";
  public static final String VIEW_CONTEXT = "views";

  private final CrewService crewService;
  private final TreasureService treasureService;
  private final ViewService viewService;


  private <E extends Entity> URI toURI(E entity, String context) {
    return URI.create(BASE_PATH + context + "/" + entity.getId());
  }

  @PostMapping(TREASURE_CONTEXT)
  public Mono<ResponseEntity<Treasure>> createTreasure(@RequestBody @Validated Treasure entity) {
    entity.setId(null);
    return treasureService.create(entity)
        .map(t -> ResponseEntity.created(toURI(t, TREASURE_CONTEXT)).body(t));
  }

  @GetMapping(TREASURE_CONTEXT)
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasures() {
    return Mono.just(ResponseEntity.ok(treasureService.getAll()));
  }

  @GetMapping(TREASURE_CONTEXT + "/{id}")
  public Mono<ResponseEntity<Treasure>> getTreasure(@PathVariable String id) {
    return treasureService.get(id).map(ResponseEntity::ok);
  }

  @GetMapping(TREASURE_CONTEXT + "/radius")
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasuresInRadius(@RequestParam float x,
      @RequestParam float y,
      @RequestParam Optional<Float> radius) {
    return Mono.just(ResponseEntity.ok(treasureService.getTreasuresInRadius(x, y, radius)));
  }

  @PatchMapping(TREASURE_CONTEXT + "/{id}/assignedCrews")
  public Mono<ResponseEntity<Treasure>> assignCrew(@PathVariable String id,
      @RequestParam String crew) {
    return treasureService.assign(crew, id).map(ResponseEntity::ok);
  }

  @PostMapping(CREW_CONTEXT)
  public Mono<ResponseEntity<Crew>> createCrew(@RequestBody @Validated Crew entity) {
    return crewService.create(entity)
        .map(t -> ResponseEntity.created(toURI(t, CREW_CONTEXT)).body(t));
  }

  @GetMapping(CREW_CONTEXT)
  public Mono<ResponseEntity<Flux<Crew>>> getCrews() {
    return Mono.just(ResponseEntity.ok(crewService.getAll()));
  }

  @GetMapping(CREW_CONTEXT + "/{id}")
  public Mono<ResponseEntity<Crew>> getCrew(@PathVariable String id) {
    return crewService.get(id).map(ResponseEntity::ok);
  }

  @GetMapping(VIEW_CONTEXT + "/" + CREW_CONTEXT + "/{id}")
  public Mono<ResponseEntity<CrewView>> getCrewView(@PathVariable String id) {
    return viewService.getCrewView(id).map(ResponseEntity::ok);
  }

  @GetMapping(VIEW_CONTEXT + "/" + TREASURE_CONTEXT + "/{id}")
  public Mono<ResponseEntity<TreasureView>> getTreasureView(@PathVariable String id) {
    return viewService.getTreasureView(id).map(ResponseEntity::ok);
  }


}
