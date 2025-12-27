# Microservices

Microservices Architecture: General Concepts and Implementations in the Spring Ecosystem

# General Concepts & Technologies

- API Gateway → [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)
- Auth Server → [KeyCloak](https://www.keycloak.org/) → [Spring OAuth2 Resource Server](https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/index.html)
- Config Server → [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
- Secrets Management → [AWS Secrets Manager](https://aws.amazon.com/secrets-manager/)
- Service Discovery → [Consul](https://developer.hashicorp.com/consul) → [Spring Cloud Consul](https://spring.io/projects/spring-cloud-consul)
- Leader Election → [Apache ZooKeeper](https://zookeeper.apache.org/) → [Spring Integration Zookeeeper](https://spring.io/projects/spring-integration)
- Resilience
  - Load Balancer (client side) → [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
  - Circuit Breaker, Retry, Timeout → [Spring Cloud Circuit Breaker](https://spring.io/projects/spring-cloud-circuitbreaker)
    - Rate limiting will be done at the gateway.
- Observability Concepts
  - Log Aggregation → [Logstash](https://www.elastic.co/logstash) & [Elasticsearch](https://www.elastic.co/elasticsearch) & [Kibana](https://www.elastic.co/kibana) (ELK Stack)
  - Metrics Aggregation, Alerting, Monitoring → [Prometheus](https://prometheus.io/) + [Perses](https://github.com/perses/perses)
  - Distributed Tracing → [Zipkin](https://zipkin.io/)
- Health check → [Spring Boot Actuator](https://docs.spring.io/spring-boot/how-to/actuator.html)

# Technologies & Platforms

> [!NOTE]
> I mentioned my preferences above; other alternatives are listed below.

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

- [Logstash](https://www.elastic.co/logstash) & [Elasticsearch](https://www.elastic.co/elasticsearch) & [Kibana](https://www.elastic.co/kibana) (ELK Stack)
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
