package com.arrr.piratery.commons.base.services.norm;

public interface EntityMapper<PO, DO> {

  PO toPO(DO domainObject);

}
