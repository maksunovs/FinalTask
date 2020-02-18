package com.epam.final_task.model.entity;

import com.epam.final_task.model.entity.enums.TrackState;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Data
@Table(name = "tracks")
public class Track implements Comparable<Track>, Serializable {
    private Long id;
    private Artist artist;
    private String title;
    private String genre;
    private Album album;
    private BigDecimal price;
    private TrackState state = TrackState.IN_STORE;


    public Track(Artist artist, String title, String genre, BigDecimal price) {
        this.artist = artist;
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    @Override
    public int compareTo(Track o) {
        int result = this.artist.getName().compareTo(o.artist.getName());
        if(result==0){
            result=this.title.compareTo(o.title);
        }
        return result;
    }

}
