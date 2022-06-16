package main

//TODO: working validation/parsing?
//bash-script for testing feature parity of APIs

import (
	"log"
	"runtime"
	"time"

	"github.com/DeanThompson/ginpprof"
	"github.com/gin-gonic/gin"
	"github.com/kamva/mgm/v3"
	"github.com/shopspring/decimal"
)

func createRouter() *gin.Engine {
	r := gin.Default()
	ginpprof.Wrap(r)

	return r
}

func System() {
	mem := &runtime.MemStats{}

	for {
		cpu := runtime.NumCPU()
		log.Println("CPU:", cpu)

		rot := runtime.NumGoroutine()
		log.Println("Goroutine:", rot)

		// Byte
		runtime.ReadMemStats(mem)
		log.Println("Memory:", mem.Alloc/1000000)

		time.Sleep(5 * time.Second)
		log.Println("-------")
	}
}

func DemoData() {
	treasure1 := Treasure{mgm.DefaultModel{}, "treasure1", "Owner1", Position{1, 2},
		decimal.NewFromInt(10), []string{}}
	treasure2 := Treasure{mgm.DefaultModel{}, "treasure2", "Owner2", Position{2, 3},
		decimal.NewFromInt(15), []string{}}
	treasure3 := Treasure{mgm.DefaultModel{}, "treasure3", "Owner3", Position{3, 4},
		decimal.NewFromInt(20), []string{}}
	treasure4 := Treasure{mgm.DefaultModel{}, "treasure4", "Owner4", Position{300, 400},
		decimal.NewFromInt(20), []string{}}

	treasure := CreateTreasure(treasure1)
	CreateTreasure(treasure2)
	CreateTreasure(treasure3)
	CreateTreasure(treasure4)

	crewPO1 := Crew{mgm.DefaultModel{}, "crew1", decimal.NewFromInt(100), decimal.Zero, []string{}}
	crewPO2 := Crew{mgm.DefaultModel{}, "crew3", decimal.NewFromInt(100), decimal.Zero, []string{}}
	crewPO3 := Crew{mgm.DefaultModel{}, "crew3", decimal.NewFromInt(150), decimal.Zero, []string{}}

	crew1 := CreateCrew(crewPO1)
	CreateCrew(crewPO2)
	CreateCrew(crewPO3)

	Assign(treasure.ID.Hex(), crew1.ID.Hex())
}

func main() {
	go System()

	InitDB()
	DemoData()
	r := createRouter()

	CrewController(r)
	TreasureController(r)

	r.Run(":8080")

}
