package com.arrr.piratery.commons.base.mixins.core;

import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GetRepository<E extends Entity> {

  ReactiveCrudRepository<E, String> getRepository();
}
