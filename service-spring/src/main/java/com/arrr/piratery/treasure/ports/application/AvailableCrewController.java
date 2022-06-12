package com.arrr.piratery.treasure.ports.application;

import static com.arrr.piratery.commons.base.controllers.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.mixins.controllers.GetEntityControllerMixin;
import com.arrr.piratery.treasure.domain.AvailableCrew;
import com.arrr.piratery.treasure.services.domain.AvailableCrewService;
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
@RequestMapping(BASE_PATH + "/available-crews")
@AllArgsConstructor
@Getter
public class AvailableCrewController implements
    GetEntityControllerMixin<AvailableCrew> {

  private final AvailableCrewService service;

  @GetMapping
  public Mono<ResponseEntity<Flux<AvailableCrew>>> getAvailableCrews() {
    return getAll();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<AvailableCrew>> getAvailableCrew(@PathVariable String id) {
    return get(id);
  }
}
