# Microservices

Microservices Architecture: General Concepts and Implementations in the Spring Ecosystem

# General Concepts & Technologies

- API Gateway → [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- Auth
  Server → [KeyCloak](https://www.keycloak.org/) → [Spring OAuth2 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
- Config Server → [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
- Secrets Management → [AWS Secrets Manager](https://aws.amazon.com/secrets-manager/)
- Service
  Discovery → [Consul](https://developer.hashicorp.com/consul) → [Spring Cloud Consul](https://spring.io/projects/spring-cloud-consul)
- Leader
  Election → [Apache ZooKeeper](https://zookeeper.apache.org/) → [Spring Integration Zookeeeper](https://spring.io/projects/spring-integration)
- Resilience
    - Load Balancer (client side) → [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
    - Circuit Breaker, Retry,
      Timeout → [Spring Cloud Circuit Breaker](https://spring.io/projects/spring-cloud-circuitbreaker)
        - Rate limiting will be done at the gateway.
- Observability Concepts
    - Log
      Aggregation → [Logstash](https://www.elastic.co/logstash) & [Elasticsearch](https://www.elastic.co/elasticsearch) & [Kibana](https://www.elastic.co/kibana) (
      ELK Stack)
    - Metrics Aggregation, Alerting,
      Monitoring → [Prometheus](https://prometheus.io/) + [Perses](https://github.com/perses/perses)
    - Distributed Tracing → [Zipkin](https://zipkin.io/)
- Health check → [Spring Boot Actuator](https://docs.spring.io/spring-boot/how-to/actuator.html)

## Running the Application

1. Clone repository

```
git clone https://github.com/sevketbuyukdemir/microservices.git
```

2. Run services via Docker Desktop

- Create and start container for the first time:

```
docker-compose up -d
```

- Start an existing container:

```
docker-compose start
```

- Stop the running container:

```
docker-compose stop
```

- Perform a clean restart (stops containers and removes volumes):

```
docker-compose down -v
```

# Services and Their External Ports

| Service       | Host Port | Description                           | Web UI URL                             |
|---------------|-----------|---------------------------------------|----------------------------------------|
| Zookeeper     | 2181      | Client connections for Zookeeper      | -                                      |
| ZooNavigator  | 9000      | Web UI for Zookeeper                  | [ZooNavigator](http://localhost:9000)  |
| RabbitMQ      | 5672      | AMQP messaging port                   | -                                      |
| RabbitMQ      | 15672     | RabbitMQ management web UI            | -                                      |
| Redis         | 6379      | Redis default port                    | -                                      |
| Kafka         | 9092      | Kafka broker port                     | -                                      |
| Kafka UI      | 8082      | Web UI for Kafka                      | [Kafka UI](http://localhost:8082)      |
| PostgreSQL    | 5432      | Keycloak database                     | -                                      |
| Keycloak      | 8080      | Keycloak admin console                | [Keycloak](http://localhost:8080)      |
| MySQL         | 3306      | MySQL database                        | -                                      |
| Consul        | 8500      | Consul web UI and API                 | [Consul](http://localhost:8500)        |
| Prometheus    | 9090      | Prometheus web UI                     | [Prometheus](http://localhost:9090)    |
| Perses        | 8081      | Perses dashboard                      | [Perses](http://localhost:8081)        |
| Zipkin        | 9411      | Zipkin tracing UI                     | [Zipkin](http://localhost:9411)        |
| Elasticsearch | 9200      | Elasticsearch REST API                | [Elasticsearch](http://localhost:9200) |
| Kibana        | 5601      | Kibana web UI for Elasticsearch       | [Kibana](http://localhost:5601)        |
| Gitea         | 3000      | Git server                            | [Gitea](http://localhost:3000)         |
| API Gateway   | 4000      | Routes requests to microservices      |                                        |
| Config Server | 4001      | central config, registers with Consul |                                        |

# Technologies & Platforms

> [!NOTE]
> I mentioned my preferences above (General Concepts & Technologies); other alternatives are listed below.

### API Gateway

- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- [Kong Gateway](https://konghq.com/products/kong-gateway)
- [Apache APISIX](https://apisix.apache.org/)
- [AWS API Gateway](https://aws.amazon.com/api-gateway/)
- [KrakenD](https://www.krakend.io/)

### Authorization Servers

- [KeyCloak](https://www.keycloak.org/)
- [Auth0](https://auth0.com/)
- [Okta](https://www.okta.com/)
- [Ory](https://github.com/ory)

### Config Servers

- [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
- [Consul](https://developer.hashicorp.com/consul)
- [etcd](https://etcd.io/)
- [Apollo](https://github.com/apolloconfig/apollo)
- [Steeltoe Config Server (.NET)](https://steeltoe.io/)

### Secrets Management

- [HashiCorp Vault](https://www.hashicorp.com/en/products/vault)
- [Kubernetes Secrets](https://kubernetes.io/docs/concepts/configuration/secret/)
- [AWS Secrets Manager](https://aws.amazon.com/secrets-manager/)

### Service Discovery

- [Consul](https://developer.hashicorp.com/consul)
- [Eureka](https://github.com/Netflix/eureka)
- [Zookeeper](https://zookeeper.apache.org/)
- [Kubernetes](https://kubernetes.io/docs/concepts/services-networking/service/#cloud-native-service-discovery)

### Leader Election

- [Apache Zookeeper](https://zookeeper.apache.org/)

### Observability

#### Distributed Tracing

- [OpenTelemetry](https://opentelemetry.io/)
- [Jaeger](https://www.jaegertracing.io/)
- [Zipkin](https://zipkin.io/)

#### Metrics Monitoring

- [Prometheus](https://prometheus.io/)
- [Prometheus](https://prometheus.io/) + [Grafana](https://grafana.com/)
- [Prometheus](https://prometheus.io/) + [Perses](https://github.com/perses/perses)
- [Zabbix](https://www.zabbix.com/)

#### Log Aggregation

- [Logstash](https://www.elastic.co/logstash) & [Elasticsearch](https://www.elastic.co/elasticsearch) & [Kibana](https://www.elastic.co/kibana) (
  ELK Stack)
- [fluentd](https://www.fluentd.org/)
- [Grafana Loki](https://github.com/grafana/loki)
- [graylog](https://graylog.org/)

#### Full Observability Platforms

- [Datadog](https://www.datadoghq.com/)
- [CloudWatch](https://aws.amazon.com/cloudwatch/)
- [honeycomb](https://www.honeycomb.io/)
- [New Relic](https://newrelic.com/)
- [Dynatrace](https://www.dynatrace.com/)
- [SigNoz](https://signoz.io/)
