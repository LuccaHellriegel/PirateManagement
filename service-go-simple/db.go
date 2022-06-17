package main

import (
	"fmt"

	"github.com/kamva/mgm/v3"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo/options"
)

func InitDB() {
	connectionURI := "mongodb://localhost:27017"

	err := mgm.SetDefaultConfig(nil, "test", options.Client().ApplyURI(connectionURI))

	if err != nil {
		panic(err)
	}
}

func GetAll[E any](coll_name string) []E {
	result := []E{}
	cursor, err := mgm.CollectionByName(coll_name).Find(mgm.Ctx(), bson.D{})
	if err != nil {
		panic(err)
	}

	err2 := cursor.All(mgm.Ctx(), &result)
	if err2 != nil {
		fmt.Println(err2)
		panic(err)
	}

	return result
}

//couldnt get the generics right, so we just hand in the pointer
func Get[PT mgm.Model](coll_name string, p PT, id string) {
	err := mgm.CollectionByName(coll_name).FindByID(id, p)
	if err != nil {
		panic(err)
	}
}
