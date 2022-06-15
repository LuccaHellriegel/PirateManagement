package com.arrr.piratery.crew.ports.domain;

import com.arrr.piratery.crew.domain.AvailableTreasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AvailableTreasureRepository extends
    ReactiveMongoRepository<AvailableTreasure, String> {

}