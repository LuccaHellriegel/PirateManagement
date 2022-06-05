package com.arrr.piratery.commons.base.services;

public interface EntityMapper<PO, DO> {

  PO toPO(DO domainObject);

}
