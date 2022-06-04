package com.arrr.piratery.ports.application;

import static com.arrr.piratery.commons.base.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.BaseController;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.services.domain.TreasureService;
import java.net.URI;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@AllArgsConstructor
public class TreasureController extends BaseController<Treasure> {

  private final TreasureService treasureService;

  @Override
  public URI toURI(Treasure entity) {
    return URI.create(BASE_PATH + "/treasures/" + entity.getId());
  }

  @PostMapping()
  public Mono<ResponseEntity<Treasure>> createTreasure(@RequestBody @Validated Treasure treasure) {
    return treasureService.save(treasure).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }

  @GetMapping()
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasures() {
    return Mono.just(ResponseEntity.ok(treasureService.getAll()));
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Treasure>> getTreasure(@PathVariable String id) {
    return treasureService.get(id).map(ResponseEntity::ok);
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteTreasure(@PathVariable String id) {
    return treasureService.delete(id).thenReturn(ResponseEntity.noContent().build());
  }

  @GetMapping("/radius")
  public Mono<ResponseEntity<Flux<Treasure>>> getTreasuresInRadius(@RequestParam float x,
      @RequestParam float y, @RequestParam Optional<Float> radius) {
    return Mono.just(ResponseEntity.ok(treasureService.getTreasuresInRadius(x, y, radius)));
  }
}
