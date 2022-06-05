package com.arrr.piratery.services.domain;

import com.arrr.piratery.commons.base.EntityRepository;
import com.arrr.piratery.domain.Crew;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.ports.domain.CrewRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CrewService extends EntityRepository<Crew> {

  public CrewService(CrewRepository repository) {
    super(Crew.class, repository);
  }
}
