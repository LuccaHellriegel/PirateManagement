package com.arrr.piratery.commons.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateEntityException extends BaseException {

  public DuplicateEntityException(
      Class entityClass) {
    super("DUPLICATE_" + toEntityType(entityClass).toUpperCase(),
        "Duplicate " + toEntityType(entityClass)
            + " detected. Either during a creation or update attempt.",
        HttpStatus.BAD_REQUEST);
  }

}
