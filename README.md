# hazelcast tutorial

### Overview

- Spring Boot 2.3.2.RELEASE
- Embedded Hazelcast 4.0.2
- In Memory Data Grid
    - 데이터는 항상 서버의 메인 메모리에 저장
    - 모든 클러스터 멤버는 같은 데이터를 갖는다. 
    > 데이터를 분산 저장하고, 백업 데이터를 다른 서버에 저장한다. 만약 서버 한대가 장애 시 백업 데이터를 갖고 있는 나머지 서버에서 데이터 제공
    - 클러스터 멤버는 CPU, RAM의 상태에 따라서 동적으로 추가 또는 제거 할 수 있다. (확장성)
    - 메모리에 저장된 데이터는 외부 저장소와 연동 가능
    - Java Map API 는 분산된 Key-Value 데이터에 접근할 수 있다.

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
