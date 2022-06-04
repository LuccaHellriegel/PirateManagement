package com.arrr.piratery.commons.base.error;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;

public interface NormalisingEntityError<PO extends PersistenceObject, DO extends DomainObject> extends
    EntityError<PO> {

  BaseException invalid(DO entity);

}
