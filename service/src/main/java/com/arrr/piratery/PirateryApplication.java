package com.arrr.piratery;

import com.arrr.piratery.domain.Position;
import com.arrr.piratery.domain.Treasure;
import com.arrr.piratery.ports.domain.TreasureRepository;
import java.math.BigDecimal;
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
  public CommandLineRunner demo(TreasureRepository treasureRepository) {
    return (args) -> {
      Treasure treasure1 = new Treasure("id1", "Owner1", new Position(1, 2),
          BigDecimal.valueOf(10));
      Treasure treasure2 = new Treasure("id2", "Owner2", new Position(2, 3),
          BigDecimal.valueOf(15));
      Treasure treasure3 = new Treasure("id3", "Owner3", new Position(3, 4),
          BigDecimal.valueOf(20));

      Flux.just(treasure1, treasure2, treasure3).map(treasureRepository::save)
          .subscribe(result -> System.out.println("Created treasure : " + result.block()));
    };
  }

}
