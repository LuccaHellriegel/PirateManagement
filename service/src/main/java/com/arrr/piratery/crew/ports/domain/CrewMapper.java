package com.arrr.piratery.crew.ports.domain;

import com.arrr.piratery.commons.base.services.EntityMapper;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.treasure.domain.Treasure;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface CrewMapper extends EntityMapper<CrewPO, Crew> {

  @Mappings({
      @Mapping(target = "assignedTreasures", source = "assignedTreasures", ignore = true)
  })
  Crew partialToDO(CrewPO persistenceObject);

  default Set<String> map(Set<Treasure> value) {
    return value.stream().map(Treasure::getId).collect(Collectors.toSet());
  }
}
