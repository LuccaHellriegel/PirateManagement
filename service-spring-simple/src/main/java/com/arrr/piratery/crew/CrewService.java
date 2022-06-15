package com.arrr.piratery.crew;

import com.arrr.piratery.commons.exceptions.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.HashSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Getter
public class CrewService {

  CrewRepository repository;

  public Mono<Crew> create(Crew persistenceObject) {
    persistenceObject.setId(null);
    persistenceObject.setAssignedTreasures(new HashSet<>());
    persistenceObject.setUsedCapacity(BigDecimal.ZERO);
    return repository.insert(persistenceObject);
  }

  public Mono<Crew> get(String crewId) {
    return repository.findById(crewId)
        .switchIfEmpty(Mono.error(new EntityNotFoundException(Crew.class, crewId)));
  }

  public Flux<Crew> getAll() {
    return repository.findAll();
  }

}
