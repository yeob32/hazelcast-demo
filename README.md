# hazelcast tutorial

### Overview

- Spring Boot 2.3.2.RELEASE
- Embedded Hazelcast 4.0.2
- In Memory Data Grid

### Clustering
- Multicast discovery
- Discovery by TCP/IP
- AWS EC2 discovery by TCP/IP
- jclouds® for discovery

### Data Structure
- Map
    - optimistic lock vs pessimistic lock
- Queue
    - Producer Consumer Pattern
- Set
- List
- RingBuffer
- Topic
- Reliable Topic
    - Slow Consumer
    - reliable topic vs topic
    
### Event Listeners

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

## 참고

* https://docs.hazelcast.org/docs/4.0.2/manual/html-single/index.html#rolling-member-upgrades
* https://docs.spring.io/spring/docs/current/spring-framework-reference/integration.html#cache
* https://brunch.co.kr/@springboot/56
* https://hazelcast.com/
* https://octoperf.com/blog/2018/06/12/spring-boot-hazelcast-tutorial/
