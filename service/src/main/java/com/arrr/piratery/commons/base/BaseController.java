package com.arrr.piratery.commons.base;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(BaseController.BASE_PATH)
abstract public class BaseController {

  public static final String BASE_PATH = "/api/v1";

}
