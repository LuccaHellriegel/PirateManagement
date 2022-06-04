package com.arrr.piratery.ports.domain;

import com.arrr.piratery.domain.Treasure;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TreasureRepository extends ReactiveCrudRepository<Treasure, String> {

}
