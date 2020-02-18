package com.epam.final_task.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name="artists")
public class Artist implements Comparable<Artist>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums;

    @OneToMany(mappedBy = "artist")
    private List<Track> tracks;

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    @Override
    public int compareTo(Artist o) {
        return this.name.compareTo(o.name);
    }

}
