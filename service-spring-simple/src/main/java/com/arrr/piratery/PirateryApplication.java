package com.arrr.piratery;

import com.arrr.piratery.crew.Crew;
import com.arrr.piratery.crew.CrewService;
import com.arrr.piratery.treasure.Treasure;
import com.arrr.piratery.treasure.Treasure.Position;
import com.arrr.piratery.treasure.TreasureService;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class PirateryApplication {

  public static void main(String[] args) {
    SpringApplication.run(PirateryApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(
      TreasureService treasureService,
      CrewService crewService) {
    return (args) -> {
      Treasure treasure1 = new Treasure(null, "treasure1", "Owner1", new Position(1, 2),
          BigDecimal.valueOf(10), Set.of());
      Treasure treasure2 = new Treasure(null, "treasure2", "Owner2", new Position(2, 3),
          BigDecimal.valueOf(15), Set.of());
      Treasure treasure3 = new Treasure(null, "treasure3", "Owner3", new Position(3, 4),
          BigDecimal.valueOf(20), Set.of());
      Treasure treasure4 = new Treasure(null, "treasure4", "Owner4", new Position(300, 400),
          BigDecimal.valueOf(20), Set.of());

      var treasureFlux = Flux.just(treasure1, treasure2, treasure3, treasure4)
          .flatMap(treasureService::create)
          .doOnNext(result -> System.out.println("Created treasure : " + result));

      Crew crewPO1 = new Crew(null, "crew1", BigDecimal.valueOf(100), BigDecimal.ZERO, Set.of());
      Crew crewPO2 = new Crew(null, "crew3", BigDecimal.valueOf(100), BigDecimal.ZERO, Set.of());
      Crew crewPO3 = new Crew(null, "crew3", BigDecimal.valueOf(150), BigDecimal.ZERO, Set.of());

      var crewFlux = Flux.just(crewPO1, crewPO2, crewPO3).flatMap(crewService::create)
          .doOnNext(result -> System.out.println("Created crew : " + result));

      Mono.zip(
              treasureFlux.collectList().map(l -> l.get(0)),
              crewFlux.collectList().map(l -> l.get(0)))
          .delayElement(Duration.ofSeconds(5))
          .flatMap(t ->
              treasureService.assign(t.getT2().getId(), t.getT1().getId())
          )
          .subscribe();
    };
  }
}
