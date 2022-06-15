package com.arrr.piratery.treasure.ports.domain;

import com.arrr.piratery.commons.events.crew.CrewUpdated;
import com.arrr.piratery.treasure.domain.AvailableCrew;
import com.arrr.piratery.treasure.domain.Treasure;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface TreasureMapper {

  @Mappings({
      @Mapping(target = "assignedCrews", source = "assignedCrews", ignore = true),
      @Mapping(target = "assignCrew", ignore = true)
  })
  Treasure partialToDO(TreasurePO persistenceObject);

  default Set<String> map(Set<AvailableCrew> value) {
    return value.stream().map(AvailableCrew::getId).collect(Collectors.toSet());
  }

  AvailableCrew toAvailableCrew(CrewUpdated crewUpdated);

  TreasurePO toPO(Treasure domainObject);

}
