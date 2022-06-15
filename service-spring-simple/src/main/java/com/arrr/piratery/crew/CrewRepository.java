package com.arrr.piratery.crew;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CrewRepository extends ReactiveMongoRepository<Crew, String> {

}
