package com.example.demo.config;

import com.hazelcast.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HazelcastConfiguration {

    @Bean
    public Config hazelcastConfig() {
        List<String> members = new ArrayList<>();
        members.add("127.0.0.1");

        TcpIpConfig TcpIpConfig = new TcpIpConfig()
                .setEnabled(true)
                .setMembers(members)
                .setConnectionTimeoutSeconds(10);

        Config config = new Config();
        config.setInstanceName("ballon-hazelcast-instance");
        config.addMapConfig(new MapConfig()
                .setName("ballons")
                .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                .setEvictionPolicy(EvictionPolicy.LRU)
                .setTimeToLiveSeconds(20));

//        MemberAttributeConfig memberAttributeConfig = new MemberAttributeConfig();
//        memberAttributeConfig.setIntAttribute("port", 5901);
//        config.setMemberAttributeConfig(memberAttributeConfig);

        NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPortCount(20);
        networkConfig.setPort(5701);
        networkConfig.setPortAutoIncrement(true);
        networkConfig.setJoin(new JoinConfig()
                .setMulticastConfig(new MulticastConfig().setEnabled(false))
                .setTcpIpConfig(TcpIpConfig));
        networkConfig.setPortCount(20);

        config.setNetworkConfig(networkConfig);

        return config;
    }

}
