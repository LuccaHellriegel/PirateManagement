package com.arrr.piratery.commons.base.mixins.controllers;

import static com.arrr.piratery.commons.base.controllers.BaseController.BASE_PATH;

import com.arrr.piratery.commons.base.types.Entity;
import java.net.URI;

public interface ToURIMixin<E extends Entity> {
  String getContext();

  default URI toURI(E entity) {
    return URI.create(BASE_PATH + "/" + getContext() + "/" + entity.getId());
  }
}
