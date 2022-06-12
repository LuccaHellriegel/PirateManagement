package com.arrr.piratery.commons.base.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidEntityException extends BaseException {

  public InvalidEntityException(
      Class entityClass,
      String message) {
    super("INVALID_" + toEntityType(entityClass).toUpperCase(),
        "Invalid " + toEntityType(entityClass)
            + " detected. " + message,
        HttpStatus.BAD_REQUEST);
  }

  public InvalidEntityException(
      Class entityClass) {
    super("INVALID_" + toEntityType(entityClass).toUpperCase(),
        "Invalid " + toEntityType(entityClass)
            + " detected. ",
        HttpStatus.BAD_REQUEST);
  }

}
