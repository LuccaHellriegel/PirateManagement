package com.arrr.piratery.ports.domain;

import com.arrr.piratery.domain.Crew;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface CrewRepository extends ReactiveMongoRepository<Crew, String>,
    ReactiveQuerydslPredicateExecutor<Crew> {

}
