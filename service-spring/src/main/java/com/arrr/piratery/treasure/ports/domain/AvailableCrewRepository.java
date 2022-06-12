package com.arrr.piratery.treasure.ports.domain;

import com.arrr.piratery.treasure.domain.AvailableCrew;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AvailableCrewRepository extends ReactiveMongoRepository<AvailableCrew, String> {

}