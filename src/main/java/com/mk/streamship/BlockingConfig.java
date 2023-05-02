package com.mk.streamship;

import com.mk.streamship.schema.Device;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@Profile("blocking")
public class BlockingConfig {

  @Bean
  public Supplier<Device> producer(SecureRandom secureRandom) {
    return () ->
    {
      Device event = new Device(UUID.randomUUID().toString(),
          secureRandom.nextInt(), LocalDateTime.now());
      //log.info("Publishing event {}",event);
      return event;
    };
  }

  @Bean
  public Consumer<Device> consumer() {
    return deviceEvent -> log.info("Evento [{}]", deviceEvent);
  }
}
