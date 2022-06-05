package com.arrr.piratery.ports.application;

import static com.arrr.piratery.commons.base.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.BaseEntityController;
import com.arrr.piratery.domain.Crew;
import com.arrr.piratery.services.domain.CrewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(BASE_PATH + "/crews")
public class CrewController extends BaseEntityController<Crew> {

  public CrewController(CrewService service) {
    super(service, "crews");
  }

  @PostMapping()
  public Mono<ResponseEntity<Crew>> createCrew(@RequestBody @Validated Crew crew) {
    return service.save(crew).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }
}
