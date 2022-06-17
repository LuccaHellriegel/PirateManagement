package main

import (
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/gin-gonic/gin"
)

var TREASURE_CONTEXT = "/treasures/"

func TreasureController(r *gin.RouterGroup) {

	r.POST(TREASURE_CONTEXT, func(c *gin.Context) {
		treasure := Treasure{}
		err := c.BindJSON(treasure)
		if err != nil {
			panic(err)
		}
		createdTreasure := CreateTreasure(treasure)
		//TODO: uri in location header?
		c.BindUri(createdTreasure)
		c.JSON(http.StatusCreated, createdTreasure)
	})

	r.GET(TREASURE_CONTEXT, func(c *gin.Context) {
		fmt.Println(GetAllTreasures())
		c.JSON(http.StatusOK, GetAllTreasures())
	})

	r.GET(TREASURE_CONTEXT+":id", func(c *gin.Context) {
		c.JSON(http.StatusOK, GetTreasure(c.Param("id")))
	})

	r.PATCH(TREASURE_CONTEXT+":id/assignedCrews", func(c *gin.Context) {
		crewId := c.Query("crew")
		treasureId := c.Param("id")
		c.JSON(http.StatusOK, Assign(treasureId, crewId))
	})

	r.GET(TREASURE_CONTEXT+"radius", func(c *gin.Context) {
		radiusStr := c.Query("radius")
		xStr := c.Query("x")
		yStr := c.Query("y")

		var radius float32
		var x float32
		var y float32

		err := json.Unmarshal([]byte(radiusStr), &radius)
		if err == nil {
			//TODO: move to treasure.go
			radius = DEFAULT_RADIUS
		}
		err2 := json.Unmarshal([]byte(xStr), &x)
		err3 := json.Unmarshal([]byte(yStr), &y)
		if err2 != nil {
			panic(err2)
		}
		if err3 != nil {
			panic(err2)
		}

		c.JSON(http.StatusOK, GetInRadius(x, y, radius))
	})

}
