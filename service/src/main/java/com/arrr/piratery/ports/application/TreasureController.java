package com.arrr.piratery.ports.application;

import static com.arrr.piratery.commons.base.controllers.GetEntityController.BASE_PATH;

import com.arrr.piratery.commons.base.controllers.CRUDEntityController;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.services.domain.treasure.TreasureService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(BASE_PATH + "/treasures")
public class TreasureController extends CRUDEntityController<Treasure> {

  private final TreasureService service;

  public TreasureController(TreasureService service) {
    super(service, "treasures");
    this.service = service;
  }

  @GetMapping("/radius")
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasuresInRadius(@RequestParam float x,
      @RequestParam float y, @RequestParam Optional<Float> radius) {
    return Mono.just(ResponseEntity.ok(service.getTreasuresInRadius(x, y, radius)));
  }


}
