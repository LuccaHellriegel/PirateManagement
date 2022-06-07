package com.arrr.piratery.commons.ports.domain;

import com.arrr.piratery.commons.base.services.EntityMapper;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.treasure.domain.CrewDetails;
import com.arrr.piratery.treasure.domain.Treasure;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface TreasureMapper extends EntityMapper<TreasurePO, Treasure> {

  @Mappings({
      @Mapping(target = "assignedCrews", source = "assignedCrews", ignore = true),
      @Mapping(target = "assignCrew",ignore = true)
  })
  Treasure partialToDO(TreasurePO persistenceObject);

  default Set<String> map(Set<CrewDetails> value) {
    return value.stream().map(CrewDetails::getId).collect(Collectors.toSet());
  }

  @BeanMapping(ignoreUnmappedSourceProperties = {"capacity", "assignedTreasures"})
  CrewDetails toDetails(Crew crew);

}
