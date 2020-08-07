package com.example.demo.hazelcast;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HazelcastRunner implements ApplicationRunner {

    private final HazelcastService hazelcastService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        hazelcastService.setUp();
    }
}
