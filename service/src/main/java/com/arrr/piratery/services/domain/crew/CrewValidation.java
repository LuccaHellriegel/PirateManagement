package com.arrr.piratery.services.domain.crew;

import com.arrr.piratery.commons.base.validation.BaseNormalisingEntityValidation;
import com.arrr.piratery.domain.Crew;
import com.arrr.piratery.ports.domain.crew.CrewPO;
import com.arrr.piratery.ports.domain.crew.CrewRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CrewValidation extends BaseNormalisingEntityValidation<CrewPO, Crew> {

  public CrewValidation(CrewError crewError,
      CrewRepository crewRepository) {
    super(crewError, crewRepository);
  }

  @Override
  public Mono<Boolean> hasValidProperties(Crew crew) {
    return Mono.just(crew.validate(entityError)).thenReturn(true);
  }
}
