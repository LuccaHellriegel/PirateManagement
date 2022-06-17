package main

//TODO: working validation/parsing?
//bash-script for testing feature parity of APIs

import (
	"log"
	"runtime"
	"time"

	"github.com/DeanThompson/ginpprof"
	"github.com/gin-gonic/gin"
)

func createRouter() (*gin.Engine, *gin.RouterGroup) {
	r := gin.Default()

	ginpprof.Wrap(r)

	return r, r.Group("/api/v1")
}

func System() {
	mem := &runtime.MemStats{}

	for {
		runtime.ReadMemStats(mem)
		log.Println("Memory:", mem.Alloc/1000000)

		time.Sleep(5 * time.Second)
		log.Println("-------")
	}
}

func main() {
	go System()

	InitDB()
	r, group := createRouter()

	CrewController(group)
	TreasureController(group)

	r.Run(":8080")

}
