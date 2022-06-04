package com.arrr.piratery.commons.base;


import java.net.URI;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(BaseController.BASE_PATH)
abstract public class BaseController<E> {

  public static final String BASE_PATH = "/api/v1";

  abstract public URI toURI(E entity);
}

