package com.arrr.piratery.commons.base.services;

import com.arrr.piratery.commons.base.types.Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GetRepository<E extends Entity> {

  ReactiveCrudRepository<E, String> getRepository();
}
