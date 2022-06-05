package com.arrr.piratery.commons.base.error;

import com.arrr.piratery.commons.base.controllers.ExceptionHandler.RequestError;
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

  public RequestError toError() {
    RequestError error = new RequestError();
    error.setMessage(getMessage());
    error.setCode(getErrorCode());
    error.setStatus(getStatus().value());
    return error;
  }

}
