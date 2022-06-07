package com.arrr.piratery.crew.services.domain;

import com.arrr.piratery.commons.ports.domain.CrewMapper;
import com.arrr.piratery.commons.ports.domain.CrewPO;
import com.arrr.piratery.commons.ports.domain.CrewRepository;
import com.arrr.piratery.commons.services.domain.BaseCrewService;
import com.arrr.piratery.commons.services.domain.BaseTreasureService;
import com.arrr.piratery.crew.domain.Crew;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CrewService extends BaseCrewService {

  public CrewService(CrewMapper mapper,
      BaseTreasureService treasureService,
      CrewRepository repository) {
    super(mapper, treasureService, repository);
  }

  public Mono<Crew> create(CrewPO persistenceObject) {
    persistenceObject.setId(null);
    return toDO(persistenceObject)
        .flatMap(this::validatedSave);
  }
}
