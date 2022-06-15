package com.arrr.piratery.commons.exceptions;

import java.util.Collection;
import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {

  public EntityNotFoundException(
      Class entityClass,
      String entityId) {
    super(toEntityType(entityClass).toUpperCase() + "_NOT_FOUND",
        toEntityType(entityClass) + " " + entityId + " could not be found.",
        HttpStatus.NOT_FOUND);
  }

  public EntityNotFoundException(
      Class entityClass,
      Collection<String> entityIds) {
    super(toEntityType(entityClass).toUpperCase() + "_NOT_FOUND",
        toEntityType(entityClass) + "s could not be found: " + entityIds,
        HttpStatus.NOT_FOUND);
  }

}
