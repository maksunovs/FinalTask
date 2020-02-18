package com.epam.final_task.model.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "playlists")
public class Playlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "playlists")
    private List<Track> tracks;

    public Playlist(String title) {
        this.title = title;
    }

}
