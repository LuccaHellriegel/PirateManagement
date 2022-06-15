package com.arrr.piratery.view;

import com.arrr.piratery.crew.Crew;
import com.arrr.piratery.treasure.Treasure;
import java.util.List;
import lombok.Value;

@Value
public class TreasureView {

  Treasure treasure;
  List<Crew> assignedCrews;

}
