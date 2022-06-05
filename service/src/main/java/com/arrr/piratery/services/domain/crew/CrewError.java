package com.arrr.piratery.services.domain.crew;

import com.arrr.piratery.commons.base.error.BaseEntityError;
import com.arrr.piratery.ports.domain.crew.CrewPO;
import org.springframework.stereotype.Component;

@Component
public class CrewError extends BaseEntityError {

  public CrewError() {
    super(CrewPO.class);
  }
}
