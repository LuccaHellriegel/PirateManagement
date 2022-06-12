package com.arrr.piratery.commons.services.domain;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AssignmentService {
  //TODO
//
//    private final BaseCrewService baseCrewService;
//    private final BaseTreasureService baseTreasureService;
//
//    private Mono<Tuple2<Crew, Treasure>> get(String crewId, String treasureId) {
//        return Mono.zip(baseCrewService.getDO(crewId), baseTreasureService.getDO(treasureId));
//    }
//
//    private Mono<Tuple2<Crew, Treasure>> assign(String crewId, String treasureId) {
//        return get(crewId, treasureId).flatMap(t -> {
//            var crew = t.getT1();
//            var treasure = t.getT2();
//
//            crew.assignTreasure(baseCrewService.getMapper().toDetails(treasure));
//            treasure.assignCrew(baseTreasureService.getMapper().toDetails(crew));
//
//            //we need to make sure both are valid before saving both, so can't just use validatedSave
//            return baseCrewService.validate(crew).flatMap(c ->
//                    Mono.zip(baseCrewService.save(c),
//                            baseTreasureService.save(treasure)
//                    ));
//        });
//    }
//
//    public Mono<Crew> assignTreasure(String crewId, String treasureId) {
//        return assign(crewId, treasureId).map(Tuple2::getT1);
//    }
//
//    public Mono<Treasure> assignCrew(String crewId, String treasureId) {
//        return assign(crewId, treasureId).map(Tuple2::getT2);
//    }

}
