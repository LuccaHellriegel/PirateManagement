package com.arrr.piratery.treasure.ports.domain;

import com.arrr.piratery.treasure.ports.domain.TreasurePO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;

public interface TreasureRepository extends ReactiveMongoRepository<TreasurePO, String>,
    ReactiveQuerydslPredicateExecutor<TreasurePO> {

}
