package com.example.demo.hazelcast.config;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spi.properties.ClusterProperty;
import com.hazelcast.topic.TopicOverloadPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

@Configuration
public class HazelcastConfiguration {

    private static final String INSTANCE_NAME = "yeob-instance";

    @Value("${hazelcast.members:127.0.0.1}")
    private List<String> members;

    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();
        config.setInstanceName(INSTANCE_NAME);
//        config.setProperties(clusterProperties());
        config.setNetworkConfig(networkConfig());
        config.setMemberAttributeConfig(memberAttributeConfig());

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("testMap")
                .setEvictionConfig(new EvictionConfig()
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setMaxSizePolicy(MaxSizePolicy.PER_NODE)
                        .setSize(5000))
                .setBackupCount(2) // 다른 멤버 클러스터에 백업 수, 백업은 메모리에 보관되기 때문에 메모리 사용량이 증가합니다.
                .setMaxIdleSeconds(0) // 각 항목이 맵 (TTL)에 머무르는 최대 시간 (초)입니다. 0 -> 무한
                .setTimeToLiveSeconds(300); // 각 항목이 맵 (TTL)에 머무르는 최대 시간 (초)입니다.
        // time-to-live-seconds 와 max-idle-seconds맵 항목에서 동시에 사용할 수있다.
        // 이 경우 정책 중 하나 이상에서 만료 된 것으로 표시되면 항목이 만료 된 것으로 간주됩니다.
        // 7.9.1. Slow Consumers
        ReliableTopicConfig rtConfig = config.getReliableTopicConfig("default");
        rtConfig.setTopicOverloadPolicy(TopicOverloadPolicy.BLOCK)
                .setReadBatchSize(10) // 최소 크기
                .setStatisticsEnabled(true);

        // java -Dhazelcast.property.foo=value
        config.setProperty("hazelcast.property.foo", "value");

        config.addListenerConfig(
                new ListenerConfig( "com.example.demo.hazelcast.event.ClusterMembershipListener" ) );
        config.addListenerConfig(
                new ListenerConfig("com.example.demo.hazelcast.event.ClusterMigrationListener"));
        config.addListenerConfig(
                new ListenerConfig("com.example.demo.hazelcast.event.ExampleDistObjListener"));
        config.addListenerConfig(
                new ListenerConfig("com.example.demo.hazelcast.event.NodeLifecycleListener"));

        return config;
    }

    public MemberAttributeConfig memberAttributeConfig() {
        MemberAttributeConfig memberAttributeConfig = new MemberAttributeConfig();
        memberAttributeConfig.setAttribute("mode", "one");
        memberAttributeConfig.setAttribute("groups", "api1, api1");

        return memberAttributeConfig;
    }

    @Bean
    public NetworkConfig networkConfig() {
        JoinConfig joinConfig = new JoinConfig();

        // 로컬에서 테스트 할거면 필요없음
        joinConfig.getTcpIpConfig()
                .setEnabled(true)
                .setMembers(members)
                .setConnectionTimeoutSeconds(10);
        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getAwsConfig().setEnabled(false);

        // 프로덕션 환경에서 UDP가 종종 차단되고 다른 조인 메커니즘이 보다 명확하므로 멀티캐스트 메커니즘은 프로덕션에 권장되지 않습니다.
//        Set<String> interfaces = new HashSet<>();
//        interfaces.add("192.168.1.102");
//        new MulticastConfig()
//                .setEnabled(true)
//                .setMulticastGroup("224.2.2.3")
//                .setMulticastPort(54327)
//                .setMulticastTimeToLive(32)
//                .setMulticastTimeoutSeconds(2)
//                .setTrustedInterfaces(interfaces);

        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPort(5900)
                .setPortAutoIncrement(true)
                .setPortCount(10) // default 100 -> 5701 ~ 5801
                .setJoin(joinConfig);

        return networkConfig;
    }

    @Bean
    public Properties clusterProperties() {
        Properties properties = new Properties();
//        properties.setProperty("hazelcast.logging.type", "slf4j"); // java -Dhazelcast.logging.type=slf4j
        properties.setProperty(ClusterProperty.WAIT_SECONDS_BEFORE_JOIN.getName(), "20");
        properties.setProperty(ClusterProperty.MAX_WAIT_SECONDS_BEFORE_JOIN.getName(), "120");
        properties.setProperty(ClusterProperty.MAX_JOIN_SECONDS.getName(), "600");

        return properties;
    }
}
