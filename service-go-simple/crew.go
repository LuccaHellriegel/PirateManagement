package main

import (
	"github.com/kamva/mgm/v3"
	"github.com/shopspring/decimal"
	"golang.org/x/exp/slices"
)

type Crew struct {
	mgm.DefaultModel  `bson:",inline"`
	Name              string          `validate:"required"`
	Capacity          decimal.Decimal `validate:"required"`
	UsedCapacity      decimal.Decimal `validate:"required"`
	AssignedTreasures []string        `validate:"required"`
}

func (c *Crew) calculateNewUsedCapacity(toBeAdded Treasure) decimal.Decimal {
	var assignedTreasuresSize = c.UsedCapacity.Add(toBeAdded.Size)
	if assignedTreasuresSize.Cmp(c.Capacity) > 0 {
		panic("Crew " + c.ID.Hex() + "'s capacity " + c.Capacity.String() + " was exceeded by the assigned treasure size " + assignedTreasuresSize.String() + ".")
		//TODO: learn proper error handling? return errors?
	}

	return assignedTreasuresSize
}

func (c *Crew) assignTreasure(treasure Treasure) {
	if !slices.Contains(c.AssignedTreasures, treasure.ID.Hex()) {
		c.UsedCapacity = c.calculateNewUsedCapacity(treasure)
		treasure.AssignedCrews = append(treasure.AssignedCrews, treasure.ID.Hex())
	}
}

var CREW_COLL = "crews"

func CreateCrew(crew Crew) Crew {
	//TODO: special create struct?
	// crew.SetID(nil)
	// crew.AssignedTreasures.Clear()

	result, err := mgm.CollectionByName(CREW_COLL).InsertOne(mgm.Ctx(), crew)
	if err != nil {
		panic(err)
	}

	crew.SetID(result.InsertedID)

	return crew
}

func GetAllCrews() []Crew {
	return GetAll[Crew](CREW_COLL)
}

func GetCrew(id string) Crew {
	result := &Crew{}
	Get(CREW_COLL, result, id)
	return *result
}
