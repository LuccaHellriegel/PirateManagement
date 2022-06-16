package main

type CrewView struct {
	Crew              Crew
	AssignedTreasures []Treasure
}

type TreasureView struct {
	Treasure      Treasure
	AssignedCrews []Crew
}

//TODO
// @GetMapping(VIEW_CONTEXT + "/" + CREW_CONTEXT + "/{id}")
// public Mono<ResponseEntity<CrewView>> getCrewView(@PathVariable String id) {
//   return viewService.getCrewView(id).map(ResponseEntity::ok);
// }

// @GetMapping(VIEW_CONTEXT + "/" + TREASURE_CONTEXT + "/{id}")
// public Mono<ResponseEntity<TreasureView>> getTreasureView(@PathVariable String id) {
//   return viewService.getTreasureView(id).map(ResponseEntity::ok);
// }
