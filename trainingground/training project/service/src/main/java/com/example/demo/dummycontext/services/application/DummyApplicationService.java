package com.example.demo.dummycontext.services.application;

import com.example.demo.dummycontext.domain.Dummy;
import com.example.demo.dummycontext.services.domain.DummyDomainService;
import com.example.demo.model.DummyDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DummyApplicationService {

  private final DummyDomainService domainService;

  //application services are here for preparing the data for the transport / FE
  public DummyDTO map(Dummy dummy) {
    //normally we do this mapping with MapStruct
    return new DummyDTO()
        .id(dummy.getId())
        .name(dummy.getEntityName())
        //here we add additional information that is only relevant for the FE
        .isADummy(dummy.areYouADummy());
  }

  public DummyDTO getDummy(String id) {
    return map(domainService.getDummy(id));
  }

  public List<DummyDTO> getAllDummies() {
    return domainService.getAll().stream().map(this::map).collect(Collectors.toList());
  }

  public DummyDTO createDummy(DummyDTO dummyDTO) {
    return map(domainService.createDummy(dummyDTO.getName()));
  }

}
