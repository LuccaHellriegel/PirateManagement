package com.arrr.piratery.commons.base.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(BaseController.BASE_PATH)
public interface BaseController {

    String BASE_PATH = "/api/v1";

}
