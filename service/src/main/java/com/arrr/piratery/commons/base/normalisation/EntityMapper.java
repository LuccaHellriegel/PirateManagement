package com.arrr.piratery.commons.base.normalisation;

public interface EntityMapper<PO, DO> {

  DO partialToDO(PO persistenceObject);

  PO toPO(DO domainObject);

}
