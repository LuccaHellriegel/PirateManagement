package main

import (
	"fmt"
	"os"

	"github.com/kamva/mgm/v3"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo/options"
)

func InitDB() {
	username := os.Getenv("MONGODB_USERNAME")
	password := os.Getenv("MONGODB_PASSWORD")
	clusterEndpoint := os.Getenv("MONGODB_ENDPOINT")

	//TODO
	connectionURI := fmt.Sprintf("mongodb://%s:%s@%s", username, password, clusterEndpoint)
	connectionURI = "mongodb://localhost:27017"

	err := mgm.SetDefaultConfig(nil, "mgm_lab", options.Client().ApplyURI(connectionURI))

	if err != nil {
		panic(err)
	}
}

func GetAll[E any](coll_name string) []E {
	result := []E{}
	cursor, err := mgm.CollectionByName(TREASURE_COLL).Find(mgm.Ctx(), bson.D{})
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
