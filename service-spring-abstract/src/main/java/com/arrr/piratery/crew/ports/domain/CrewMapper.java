package com.arrr.piratery.crew.ports.domain;

import com.arrr.piratery.commons.events.treasure.TreasureUpdated;
import com.arrr.piratery.crew.domain.AvailableTreasure;
import com.arrr.piratery.crew.domain.Crew;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface CrewMapper {

  @Mappings({
      @Mapping(target = "assignedTreasures", source = "assignedTreasures", ignore = true),
      @Mapping(target = "assignTreasure", ignore = true)
  })
  Crew partialToDO(CrewPO persistenceObject);

  default Set<String> map(Set<AvailableTreasure> value) {
    return value.stream().map(AvailableTreasure::getId).collect(Collectors.toSet());
  }

  AvailableTreasure toAvailableTreasure(TreasureUpdated treasureUpdated);

  CrewPO toPO(Crew domainObject);

}
