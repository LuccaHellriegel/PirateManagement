package com.arrr.piratery;

import com.arrr.piratery.crew.ports.domain.CrewPO;
import com.arrr.piratery.crew.services.domain.CrewService;
import com.arrr.piratery.treasure.domain.Position;
import com.arrr.piratery.treasure.ports.domain.TreasurePO;
import com.arrr.piratery.treasure.services.domain.TreasureService;
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
      TreasurePO treasure1 = new TreasurePO(null, "treasure1", "Owner1", new Position(1, 2),
          BigDecimal.valueOf(10), Set.of());
      TreasurePO treasure2 = new TreasurePO(null, "treasure2", "Owner2", new Position(2, 3),
          BigDecimal.valueOf(15), Set.of());
      TreasurePO treasure3 = new TreasurePO(null, "treasure3", "Owner3", new Position(3, 4),
          BigDecimal.valueOf(20), Set.of());
      TreasurePO treasure4 = new TreasurePO(null, "treasure4", "Owner4", new Position(300, 400),
          BigDecimal.valueOf(20), Set.of());

      var treasureFlux = Flux.just(treasure1, treasure2, treasure3, treasure4)
          .flatMap(treasureService::create)
          .doOnNext(result -> System.out.println("Created treasure : " + result));

      CrewPO crewPO1 = new CrewPO(null, "crew1", 100, Set.of());
      CrewPO crewPO2 = new CrewPO(null, "crew3", 100, Set.of());
      CrewPO crewPO3 = new CrewPO(null, "crew3", 150, Set.of());

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
