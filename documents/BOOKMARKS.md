[← Return to home](../README.md)

# Technologies & Platforms

> [!NOTE]
> I mentioned my preferences in [General Concepts & Technologies](GENERAL_CONCEPTS_AND_TECHNOLOGIES.md); other alternatives are listed below.

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