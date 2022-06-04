package com.arrr.piratery.commons.base.error;

import com.arrr.piratery.commons.base.types.PersistenceObject;
import java.util.Collection;

public interface EntityError<E extends PersistenceObject > {


  BaseException notFound(String entityId);

  BaseException notFound(Collection<String> entityIds);

  BaseException invalid(E entity);

  BaseException duplicate();

}
