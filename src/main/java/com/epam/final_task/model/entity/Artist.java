package com.epam.final_task.model.entity;

public class Artist implements Comparable<Artist>{
    private Integer id;
    private String name;
    private String country;

    public Artist(Integer id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public int compareTo(Artist o) {
        return this.name.compareTo(o.name);
    }
}
