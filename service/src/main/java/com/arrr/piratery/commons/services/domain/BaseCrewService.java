package com.arrr.piratery.commons.services.domain;

import com.arrr.piratery.commons.base.services.NormalisingEntityService;
import com.arrr.piratery.commons.ports.domain.CrewPO;
import com.arrr.piratery.commons.ports.domain.CrewRepository;
import com.arrr.piratery.crew.domain.Crew;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Getter
public class BaseCrewService extends NormalisingEntityService<CrewPO, Crew> {

  protected final CrewNormalisation normalisation;

  public BaseCrewService(CrewRepository repository,
      CrewNormalisation crewNormalisation) {
    super(CrewPO.class, repository, crewNormalisation);
    this.normalisation = crewNormalisation;
  }

  @Override
  public Mono<Crew> validate(Crew crew) {
    return Mono.just(crew.validate());
  }
}
