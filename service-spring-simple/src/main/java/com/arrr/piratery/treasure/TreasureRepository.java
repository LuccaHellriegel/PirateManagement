package com.arrr.piratery.treasure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface TreasureRepository extends ReactiveMongoRepository<Treasure, String>,
    ReactiveQuerydslPredicateExecutor<Treasure> {

}
