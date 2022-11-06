package com.example.demo.dummycontext.ports.domain;

import com.example.demo.dummycontext.domain.Dummy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DummyRepository extends MongoRepository<Dummy, String> {

}
