package com.arrr.piratery.ports.application;

import static com.arrr.piratery.commons.base.controllers.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.controllers.CRUDDomainObjectController;
import com.arrr.piratery.domain.Crew;
import com.arrr.piratery.ports.domain.crew.CrewPO;
import com.arrr.piratery.services.domain.crew.CrewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(BASE_PATH + "/crews")
public class CrewController extends CRUDDomainObjectController<CrewPO, Crew> {

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
}
