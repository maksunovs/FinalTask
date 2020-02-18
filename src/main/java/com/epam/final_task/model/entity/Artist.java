package com.epam.final_task.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@Table(name="artists")
public class Artist implements Comparable<Artist>, Serializable {

    private Long id;
    private String name;
    private String country;

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }


    @Override
    public int compareTo(Artist o) {
        return this.name.compareTo(o.name);
    }

}
