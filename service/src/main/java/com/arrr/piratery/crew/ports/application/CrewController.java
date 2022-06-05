package com.arrr.piratery.crew.ports.application;

import static com.arrr.piratery.commons.base.controllers.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.controllers.GetDomainObjectController;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.crew.ports.domain.CrewPO;
import com.arrr.piratery.crew.services.domain.CrewService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(BASE_PATH + "/crews")
public class CrewController extends GetDomainObjectController<CrewPO, Crew> {

  private final CrewService service;

  public CrewController(CrewService service) {
    super(service, "crews");
    this.service = service;
  }

  @PatchMapping("/{id}/assign")
  public Mono<ResponseEntity<Crew>> assignTreasure(@PathVariable String id,
      @RequestParam String treasure) {
    return service.assignTreasure(id, treasure).map(ResponseEntity::ok);
  }

  @PostMapping()
  public Mono<ResponseEntity<Crew>> create(@RequestBody @Validated CrewPO entity) {
    return service.create(entity).map(t -> ResponseEntity.created(toURI(t)).body(t));
  }
}
