package main

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

var CREW_CONTEXT = "/crews/"

func CrewController(r *gin.Engine) {

	//TODO
	r.POST(CREW_CONTEXT, func(c *gin.Context) {
		c.JSON(http.StatusOK, GetAllTreasures())
	})

	r.GET(CREW_CONTEXT, func(c *gin.Context) {
		c.JSON(http.StatusOK, GetAllCrews())
	})

	r.GET(CREW_CONTEXT+":id", func(c *gin.Context) {
		c.JSON(http.StatusOK, GetCrew(c.Param("id")))
	})

}
