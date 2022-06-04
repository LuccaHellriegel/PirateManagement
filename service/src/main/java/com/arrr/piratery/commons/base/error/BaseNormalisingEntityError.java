package com.arrr.piratery.commons.base.error;

import com.arrr.piratery.commons.base.types.DomainObject;
import com.arrr.piratery.commons.base.types.PersistenceObject;
import com.arrr.piratery.commons.base.normalisation.EntityNormalisation;

public class BaseNormalisingEntityError<PO extends PersistenceObject, DO extends DomainObject> extends
    BaseEntityError<PO> implements NormalisingEntityError<PO,DO> {

  protected final EntityNormalisation<PO,DO> entityNormalisation;

  public BaseNormalisingEntityError(EntityNormalisation<PO, DO> entityNormalisation, String entityType) {
    super(entityType);
    this.entityNormalisation = entityNormalisation;
  }

  @Override
  public BaseException invalid(DO domainObject) {
    return invalid(entityNormalisation.toPO(domainObject));
  }
}
