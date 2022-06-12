package com.arrr.piratery.crew.ports.application;

import static com.arrr.piratery.commons.base.controllers.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.mixins.controllers.GetDOControllerMixin;
import com.arrr.piratery.commons.base.mixins.controllers.ToURIMixin;
import com.arrr.piratery.commons.services.domain.AssignmentService;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.crew.ports.domain.CrewPO;
import com.arrr.piratery.crew.services.domain.CrewService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(BASE_PATH + "/crews")
@AllArgsConstructor
@Getter
public class CrewController implements
    GetDOControllerMixin<CrewPO, Crew>,
    ToURIMixin<Crew> {

  //TODO: annotation for error object

  private final CrewService service;
  private final AssignmentService assignmentService;
  private final String context = "crews";

  @PostMapping()
  public Mono<ResponseEntity<Crew>> create(@RequestBody @Validated CrewPO entity) {
    return service.create(entity).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }

  @GetMapping
  public Mono<ResponseEntity<Flux<Crew>>> getCrews() {
    return getAll();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Crew>> getCrew(@PathVariable String id) {
    return get(id);
  }


}
