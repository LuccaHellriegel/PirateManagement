package com.arrr.piratery.commons.base.error;

import java.util.Collection;

public interface EntityError {

  BaseException notFound(String entityId);

  BaseException notFound(Collection<String> entityIds);

  BaseException invalid();

  BaseException invalid(String message);

  BaseException duplicate();

}
