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

}
