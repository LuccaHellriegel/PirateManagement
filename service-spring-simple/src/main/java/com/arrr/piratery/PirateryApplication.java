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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class PirateryApplication {

  public static void main(String[] args) {
    SpringApplication.run(PirateryApplication.class, args);
  }


}
