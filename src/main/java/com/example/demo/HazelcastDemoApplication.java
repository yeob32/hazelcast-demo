package com.example.demo;

import com.example.demo.domain.music.Music;
import com.example.demo.domain.music.MusicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@AllArgsConstructor
@EnableCaching
public class HazelcastDemoApplication {

    private CacheManager cacheManager;
    private MusicService musicService;

    public static void main(String[] args) {
        SpringApplication.run(HazelcastDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            log.info("getCacheNames {}", cacheManager.getCacheNames());

            Music music = new Music(1L, "hello");
            musicService.save(music);

            log.info("not cached : {}", musicService.findMusic(music.getId()));
            log.info("maybe cached : {}", musicService.findMusic(music.getId()));
            log.info("getCacheNames {}", cacheManager.getCacheNames());

            musicService.delete(music.getId());

            musicService.save(music);
            log.info("not cached : {}", musicService.findMusic(music.getId()));
            log.info("maybe cached : {}", musicService.findMusic(music.getId()));
        };
    }
}
