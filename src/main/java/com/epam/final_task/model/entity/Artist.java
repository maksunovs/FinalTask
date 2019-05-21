package com.epam.final_task.model.entity;

public class Artist extends Entity implements Comparable<Artist> {
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Artist artist = (Artist) object;
        return this.id.equals(artist.id) &&
                this.name.equals(artist.name) &&
                this.country.equals(artist.country);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (name == null ? 0 : name.hashCode());
        hash = hash * prime + (country == null ? 0 : country.hashCode());
        return hash * prime;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
