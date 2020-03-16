package com.example.demo.domain.music;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@CacheConfig(cacheNames = "musics")
@AllArgsConstructor
public class MusicCache {

    private MusicRepository musicRepository;

    @Cacheable(key = "#id")
    public Music findMusic(Long id) {
        log.info("execute thread start {}", this.getClass().getSimpleName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("execute thread {}", this.getClass().getSimpleName());
        Optional<Music> music = musicRepository.findById(id);
        return music.orElse(null);
    }

//    @CachePut(key = "#id")
    public Music createMusic(Music music) {
        return musicRepository.save(music);
    }

    @CachePut
    public Music updateMusic(Music music) {
        return musicRepository.save(music);
    }

    @CacheEvict(key = "#id")
    public void delete(Long id) {
        musicRepository.deleteById(id);
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }


}
