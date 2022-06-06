package com.arrr.piratery.commons.ports.domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface CrewRepository extends ReactiveMongoRepository<CrewPO, String>,
    ReactiveQuerydslPredicateExecutor<CrewPO> {

}
