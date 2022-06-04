package com.arrr.piratery.commons.base.normalisation;

public interface EntityMapper<DO,PO> {

  DO partialToDO(PO persistenceObject);

  PO toPO(DO domainObject);

}
