package com.arrr.piratery.commons.base.exceptions;

import com.arrr.piratery.commons.base.exceptions.ExceptionHandler.RequestError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BaseException extends RuntimeException {

  private final String errorCode;
  private final HttpStatus status;

  protected BaseException(String errorCode, String message, HttpStatus status) {
    super(message);
    this.errorCode = errorCode;
    this.status = status;
  }

  public static String toEntityType(Class entityClass) {
    var entityType = entityClass.getSimpleName();
    if (entityType.endsWith("PO")) {
      entityType = entityType.substring(0, entityType.length() - 2);
    }
    return entityType;
  }

  public RequestError toError() {
    RequestError error = new RequestError();
    error.setMessage(getMessage());
    error.setCode(getErrorCode());
    error.setStatus(getStatus().value());
    return error;
  }

}
