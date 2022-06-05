package com.arrr.piratery.ports.domain.crew;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface CrewRepository extends ReactiveMongoRepository<CrewPO, String>,
    ReactiveQuerydslPredicateExecutor<CrewPO> {

}
