[← Return to home](../README.md)

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

> [!TIP]
> [Alternative Technologies & Platforms](BOOKMARKS.md)