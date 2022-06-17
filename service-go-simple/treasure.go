package main

import (
	"github.com/kamva/mgm/v3"
	"github.com/shopspring/decimal"
	"go.mongodb.org/mongo-driver/bson"
)

type Position struct {
	//TODO: find out how to make more involved validations like not blank, gte 0
	X float32 `validate:"required"`
	Y float32 `validate:"required"`
}

//TODO: couldnt find out how to make deserialisation with the mapset work, it cant find the unmarshal method

type Treasure struct {
	mgm.DefaultModel `bson:",inline"`
	Name             string          `validate:"required"`
	Owner            string          `validate:"required"`
	Position         Position        `validate:"required"`
	Size             decimal.Decimal `validate:"required"`
	AssignedCrews    []string        `validate:"required"`
}

func (t *Treasure) assignCrew(crew Crew) {
	t.AssignedCrews = append(t.AssignedCrews, crew.ID.Hex())
}

var TREASURE_COLL = "treasures"

func CreateTreasure(treasure Treasure) Treasure {
	//TODO: prevent this stuff
	// treasure.SetID(nil)
	// treasure.AssignedCrews.Clear()

	result, err := mgm.CollectionByName(TREASURE_COLL).InsertOne(mgm.Ctx(), treasure)
	if err != nil {
		panic(err)
	}

	treasure.SetID(result.InsertedID)

	return treasure
}

func GetAllTreasures() []Treasure {
	return GetAll[Treasure](TREASURE_COLL)
}

func GetTreasure(id string) Treasure {
	result := &Treasure{}
	Get(TREASURE_COLL, result, id)
	return *result
}

func Assign(treasureId string, crewId string) Treasure {
	var treasure = GetTreasure(treasureId)
	var crew = GetCrew(crewId)
	treasure.assignCrew(crew)
	crew.assignTreasure(treasure)

	err := mgm.CollectionByName(TREASURE_COLL).Update(&treasure)
	if err != nil {
		panic(err)
	}
	err2 := mgm.CollectionByName(CREW_COLL).Update(&crew)
	if err2 != nil {
		panic(err2)
	}

	return treasure
}

var DEFAULT_RADIUS float32 = 10

func inRadius(treasure Treasure, x float32, y float32, radius float32) bool {
	return treasure.Position.X >= (x-radius) &&
		treasure.Position.X <= (x+radius) &&
		treasure.Position.Y >= (y-radius) &&
		treasure.Position.Y <= (y+radius)
}

func GetInRadius(x float32, y float32, radius float32) []Treasure {
	//TODO: more performant filter
	cursor, err := mgm.CollectionByName(TREASURE_COLL).Find(mgm.Ctx(), bson.D{})
	if err != nil {
		panic(err)
	}
	all := []Treasure{}
	cursor.All(mgm.Ctx(), all)
	result := make([]Treasure, 0)
	for _, v := range all {
		if inRadius(v, x, y, radius) {
			result = append(result, v)
		}
	}
	return result
}
