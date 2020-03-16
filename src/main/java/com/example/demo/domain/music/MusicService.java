package com.example.demo.domain.music;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class MusicService {

    private MusicCache musicCache;

    public Music save(Music music) {
        return musicCache.createMusic(music);
    }

    public Music update(Music music) {
        return musicCache.updateMusic(music);
    }

    public Music findMusic(Long id) {
        log.info("call {}", this.getClass().getSimpleName());
        return musicCache.findMusic(id);
    }

    public List<Music> findAll() {
        return musicCache.findAll();
    }

    public void delete(Long id) {
        musicCache.delete(id);
    }
}
