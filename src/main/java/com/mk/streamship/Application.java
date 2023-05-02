package com.mk.streamship;

import java.security.SecureRandom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.schema.registry.client.ConfluentSchemaRegistryClient;
import org.springframework.cloud.stream.schema.registry.client.SchemaRegistryClient;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  SecureRandom secureRandom() {
    return new SecureRandom();
  }

  @Bean
  SchemaRegistryClient schemaRegistryClient(
      @Value("${spring.cloud.stream.schemaRegistryClient.endpoint}") String endpoint) {
    ConfluentSchemaRegistryClient client = new ConfluentSchemaRegistryClient();
    client.setEndpoint(endpoint);
    return client;
  }
}
