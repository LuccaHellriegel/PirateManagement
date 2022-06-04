package com.arrr.piratery.ports.domain;

import com.arrr.piratery.domain.Treasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface TreasureRepository extends ReactiveMongoRepository<Treasure, String>,
    ReactiveQuerydslPredicateExecutor<Treasure> {

}
