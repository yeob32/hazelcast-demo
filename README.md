# hazelcast demo

### Overview

* Spring Boot
* Embedded Hazelcast
* In Memory Data Grid

### spring-boot-starter-cache

> This abstraction is materialized by the *org.springframework.cache.Cache* and *org.springframework.cache.CacheManager* interfaces.

### Cache Abstraction

* @Cacheable: Triggers cache population.
* @CacheEvict: Triggers cache eviction.
* @CachePut: Updates the cache without interfering with the method execution.
* @Caching: Regroups multiple cache operations to be applied on a method.
* @CacheConfig: Shares some common cache-related settings at class-level.

### Providers
* infinispan
* hazelcast
* EhCache
* Couchbase
* Redis
* Caffeine
* Simple

-----
참고

* https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#cache
* https://brunch.co.kr/@springboot/56
* https://hazelcast.com/
* https://docs.hazelcast.org/docs/rn/index.html#4-0
* https://octoperf.com/blog/2018/06/12/spring-boot-hazelcast-tutorial/