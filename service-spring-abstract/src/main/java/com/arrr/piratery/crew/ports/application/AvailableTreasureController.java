package com.arrr.piratery.crew.ports.application;

import static com.arrr.piratery.commons.base.controllers.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.mixins.controllers.GetEntityControllerMixin;
import com.arrr.piratery.crew.domain.AvailableTreasure;
import com.arrr.piratery.crew.services.domain.AvailableTreasureService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(BASE_PATH + "/available-treasures")
@AllArgsConstructor
@Getter
public class AvailableTreasureController implements
    GetEntityControllerMixin<AvailableTreasure> {

  private final AvailableTreasureService service;

  @GetMapping
  public Mono<ResponseEntity<Flux<AvailableTreasure>>> getAvailableTreasures() {
    return getAll();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<AvailableTreasure>> getAvailableTreasure(@PathVariable String id) {
    return get(id);
  }
}
