package com.arrr.piratery.ports.application;

import static com.arrr.piratery.commons.base.BaseEntityController.BASE_PATH;

import com.arrr.piratery.commons.base.BaseEntityController;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.services.domain.TreasureService;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(BASE_PATH + "/treasures")
public class TreasureController extends BaseEntityController<Treasure> {

  private final TreasureService service;

  public TreasureController(TreasureService service) {
    super(service, "treasures");
    this.service = service;
  }

  @PostMapping()
  public Mono<ResponseEntity<Treasure>> createTreasure(@RequestBody @Validated Treasure treasure) {
    return service.save(treasure).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }

  @GetMapping("/radius")
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasuresInRadius(@RequestParam float x,
      @RequestParam float y, @RequestParam Optional<Float> radius) {
    return Mono.just(ResponseEntity.ok(service.getTreasuresInRadius(x, y, radius)));
  }
}
