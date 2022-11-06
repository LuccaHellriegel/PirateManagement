package com.example.demo.dummycontext.ports.application;

import com.example.demo.api.DummiesApi;
import com.example.demo.dummycontext.services.application.DummyApplicationService;
import com.example.demo.model.DummyDTO;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DummyController implements DummiesApi {

  private final DummyApplicationService applicationService;

  @Override
  public ResponseEntity<DummyDTO> createDummy(DummyDTO dummyDTO) {
    var dummy = applicationService.createDummy(dummyDTO);
    return ResponseEntity.created(URI.create("/dummy/" + dummy.getId())).body(dummy);
  }

  @Override
  public ResponseEntity<List<DummyDTO>> getAllDummies() {
    return ResponseEntity.ok(applicationService.getAllDummies());
  }

  @Override
  public ResponseEntity<DummyDTO> getDummy(String dummyId) {
    return ResponseEntity.ok(applicationService.getDummy(dummyId));
  }
}
