package com.arrr.piratery.crew.services.domain;

import com.arrr.piratery.commons.ports.domain.CrewPO;
import com.arrr.piratery.commons.ports.domain.CrewRepository;
import com.arrr.piratery.commons.services.domain.BaseCrewService;
import com.arrr.piratery.commons.services.domain.CrewNormalisation;
import com.arrr.piratery.crew.domain.Crew;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CrewService extends BaseCrewService {

  public CrewService(CrewRepository repository,
      CrewNormalisation crewNormalisation) {
    super(repository, crewNormalisation);
  }

  public Mono<Crew> create(CrewPO persistenceObject) {
    persistenceObject.setId(null);
    return normalisation.toDO(persistenceObject).flatMap(this::validate)
        .flatMap(this::validatedSave);
  }
}
