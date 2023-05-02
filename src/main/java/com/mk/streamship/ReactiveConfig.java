package com.mk.streamship;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
//@Configuration
@Profile("reactive")
public class ReactiveConfig {
  record DeviceEvent(String deviceId, LocalDateTime date, int value) {
  }

  @PollableBean
  public Supplier<Flux<DeviceEvent>> producer(SecureRandom secureRandom) {
    return () -> Flux.just(
        new DeviceEvent(UUID.randomUUID().toString(), LocalDateTime.now(), secureRandom.nextInt())
    );
  }

  @Bean
  public Function<Flux<DeviceEvent>, Mono<Void>> consumer() {
    return flux -> flux.doOnNext(
        deviceEvent -> log.info("Evento [{}]", deviceEvent)
    ).then();
  }
}
