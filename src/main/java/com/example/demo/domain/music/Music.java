package com.example.demo.domain.music;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class Music implements Serializable {

    @Id
    private Long id;

    private String name;

    @Builder
    public Music(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
