package com.epam.final_task.model.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name = "playlists")
public class Playlist implements Serializable {
    private Long id;
    private String title;



    public Playlist(String title) {
        this.title = title;
    }

}
