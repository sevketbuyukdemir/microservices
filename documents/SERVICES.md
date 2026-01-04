[← Return to home](../README.md)

# Services and Their External Ports

| Service           | Host Port | Description                           | Web UI URL                             |
|-------------------|-----------|---------------------------------------|----------------------------------------|
| Zookeeper         | 2181      | Client connections for Zookeeper      | -                                      |
| ZooNavigator      | 9000      | Web UI for Zookeeper                  | [ZooNavigator](http://localhost:9000)  |
| RabbitMQ          | 5672      | AMQP messaging port                   | -                                      |
| RabbitMQ          | 15672     | RabbitMQ management web UI            | [RabbitMQ](http://localhost:15672)     |
| Redis             | 6379      | Redis default port                    | -                                      |
| Kafka             | 9092      | Kafka broker port                     | -                                      |
| Kafka UI          | 8082      | Web UI for Kafka                      | [Kafka UI](http://localhost:8082)      |
| PostgreSQL        | 5432      | Keycloak database                     | -                                      |
| Keycloak          | 8080      | Keycloak admin console                | [Keycloak](http://localhost:8080)      |
| MySQL             | 3306      | MySQL database                        | -                                      |
| Consul            | 8500      | Consul web UI and API                 | [Consul](http://localhost:8500)        |
| Prometheus        | 9090      | Prometheus web UI                     | [Prometheus](http://localhost:9090)    |
| Perses            | 8081      | Perses dashboard                      | [Perses](http://localhost:8081)        |
| Zipkin            | 9411      | Zipkin tracing UI                     | [Zipkin](http://localhost:9411)        |
| Elasticsearch     | 9200      | Elasticsearch REST API                | [Elasticsearch](http://localhost:9200) |
| Kibana            | 5601      | Kibana web UI for Elasticsearch       | [Kibana](http://localhost:5601)        |
| API Gateway       | 4000      | Routes requests to microservices      |                                        |
| Config Server     | 4001      | central config, registers with Consul |                                        |
| Product Service   | 4002      |                                       |                                        |
| Inventory Service | 4003      |                                       |                                        |
| Order Service     | 4004      |                                       |                                        |
| Payment Service   | 4005      |                                       |                                        |