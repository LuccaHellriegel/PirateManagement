package com.arrr.piratery.commons.events.crew;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class CrewUpdated {

  String id;
  String name;

}
