package com.example.demo.dummycontext.services.domain;

import com.example.demo.dummycontext.domain.Dummy;
import com.example.demo.dummycontext.ports.domain.DummyRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DummyDomainService {

  private final DummyRepository repository;

  public Dummy createDummy(String dummyName) {
    //domain logic validation
    if (!dummyName.contains("dummy")) {
      //normally we do error handling globally
      //https://www.baeldung.com/exception-handling-for-rest-with-spring
      throw new RuntimeException("Dummy name needs to have dummy in it.");
    }

    return repository.insert(new Dummy(null, dummyName));
  }

  public Dummy getDummy(String id) {
    var dummy = repository.findById(id);
    if (dummy.isEmpty()) {
      throw new RuntimeException("Dummy could not be found");
    }
    return dummy.get();
  }

  public List<Dummy> getAll() {
    return repository.findAll();
  }

}
