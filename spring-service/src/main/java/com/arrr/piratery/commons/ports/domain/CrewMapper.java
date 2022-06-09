package com.arrr.piratery.commons.ports.domain;

import com.arrr.piratery.commons.base.services.norm.EntityMapper;
import com.arrr.piratery.crew.domain.Crew;
import com.arrr.piratery.crew.domain.TreasureDetails;
import com.arrr.piratery.treasure.domain.Treasure;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface CrewMapper extends EntityMapper<CrewPO, Crew> {

  @Mappings({
      @Mapping(target = "assignedTreasures", source = "assignedTreasures", ignore = true),
      @Mapping(target = "assignTreasure",ignore = true)
  })
  Crew partialToDO(CrewPO persistenceObject);

  default Set<String> map(Set<TreasureDetails> value) {
    return value.stream().map(TreasureDetails::getId).collect(Collectors.toSet());
  }

  @BeanMapping(ignoreUnmappedSourceProperties = {"owner", "position", "assignedCrews"})
  TreasureDetails toDetails(Treasure treasure);
}
