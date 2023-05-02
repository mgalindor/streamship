# Cloud Stream

This project includes
- Blocking
  - Supplier Stream 
  - Consumer Stream
- Reactive
    - Supplier Stream
    - Consumer Stream 

- Kafka Streaming 
- Schema registry connection

Necesario run maven to generate avro clases, command is 

```
mvn generate-sources
```

To sent schemas to Schema Registry
```
mvn schema-registry:register
```