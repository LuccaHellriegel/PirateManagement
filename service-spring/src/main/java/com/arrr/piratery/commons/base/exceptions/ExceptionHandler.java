package com.arrr.piratery.commons.base.exceptions;

import com.arrr.piratery.commons.base.exceptions.BaseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler implements ErrorWebExceptionHandler {

  private final ObjectMapper objectMapper;

  @SneakyThrows
  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    if (!(ex instanceof BaseException)) {
      throw ex;
    }

    var response = exchange.getResponse();
    response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json");
    response.setStatusCode(((BaseException) ex).getStatus());

    var bytes = objectMapper.writeValueAsBytes(((BaseException) ex).toError());
    var buffer = response.bufferFactory().wrap(bytes);

    return response.writeWith(Mono.just(buffer));
  }

  @Data
  public static class RequestError {

    private Integer status;
    private String code;
    private String message;
  }

}
