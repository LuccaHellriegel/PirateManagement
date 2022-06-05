package com.arrr.piratery;

import com.arrr.piratery.crew.ports.domain.CrewPO;
import com.arrr.piratery.treasure.domain.Position;
import com.arrr.piratery.treasure.domain.Treasure;
import com.arrr.piratery.crew.ports.domain.CrewRepository;
import com.arrr.piratery.treasure.ports.domain.TreasureRepository;
import java.math.BigDecimal;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PirateryApplication {

  public static void main(String[] args) {
    SpringApplication.run(PirateryApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(TreasureRepository treasureRepository,
      CrewRepository crewRepository) {
    return (args) -> {
      Treasure treasure1 = new Treasure("id1", "treasure1", "Owner1", new Position(1, 2),
          BigDecimal.valueOf(10));
      Treasure treasure2 = new Treasure("id2", "treasure2", "Owner2", new Position(2, 3),
          BigDecimal.valueOf(15));
      Treasure treasure3 = new Treasure("id3", "treasure3", "Owner3", new Position(3, 4),
          BigDecimal.valueOf(20));
      Treasure treasure4 = new Treasure("id4", "treasure4", "Owner4", new Position(300, 400),
          BigDecimal.valueOf(20));

      var treasureFlux = Flux.just(treasure1, treasure2, treasure3, treasure4)
          .map(treasureRepository::save)
          .subscribe(result -> System.out.println("Created treasure : " + result.block()));

      CrewPO crewPO1 = new CrewPO("crewId1", "crew1", 5, Set.of("id1"));
      CrewPO crewPO2 = new CrewPO("crewId2", "crew3", 10, Set.of());
      CrewPO crewPO3 = new CrewPO("crewId3", "crew3", 15, Set.of());

      var crewFlux = Flux.just(crewPO1, crewPO2, crewPO3).map(crewRepository::save)
          .subscribe(result -> System.out.println("Created crew : " + result.block()));


    };
  }

}
