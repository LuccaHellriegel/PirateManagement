package com.arrr.piratery.treasure.ports.application;

import static com.arrr.piratery.commons.base.controllers.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.mixins.controllers.GetDOControllerMixin;
import com.arrr.piratery.commons.base.mixins.controllers.ToURIMixin;
import com.arrr.piratery.treasure.domain.Treasure;
import com.arrr.piratery.treasure.ports.domain.TreasurePO;
import com.arrr.piratery.treasure.services.domain.TreasureService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@RestController
@RequestMapping(BASE_PATH + "/treasures")
@Getter
@AllArgsConstructor
public class TreasureController implements
    GetDOControllerMixin<TreasurePO, Treasure>,
    ToURIMixin<Treasure> {

  private final TreasureService service;
  private final String context = "treasures";

  @GetMapping("/radius")
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasuresInRadius(@RequestParam float x,
      @RequestParam float y,
      @RequestParam Optional<Float> radius) {
    return Mono.just(ResponseEntity.ok(service.getTreasuresInRadius(x, y, radius)));
  }

  @GetMapping
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasures() {
    return getAll();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Treasure>> getTreasure(@PathVariable String id) {
    return get(id);
  }

  @PostMapping()
  public Mono<ResponseEntity<Treasure>> create(@RequestBody @Validated TreasurePO entity) {
    entity.setId(null);
    return service.create(entity).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }

  @PatchMapping("/{id}/assignedCrews")
  public Mono<ResponseEntity<Treasure>> assignCrew(@PathVariable String id,
      @RequestParam String crew) {
    return service.assign(crew, id).map(ResponseEntity::ok);
  }

}
