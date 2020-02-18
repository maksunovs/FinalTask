package com.epam.final_task.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="albums")
public class Album{
    private Long id;
    private String title;
    private Artist artist;

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

}
