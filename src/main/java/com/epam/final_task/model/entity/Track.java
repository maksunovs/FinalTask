package com.epam.final_task.model.entity;

import com.epam.final_task.model.entity.enums.TrackState;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Data
@Table(name = "tracks")
public class Track implements Comparable<Track>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    private String title;

    private String genre;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany(mappedBy = "tracks")
    private List<Cart> carts;

    @ManyToMany
    @JoinTable(
            name = "users_tracks",
            joinColumns = @JoinColumn(
                    name = "track_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "playlists_tracks",
            joinColumns = @JoinColumn(
                    name = "track_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "playlist_id", referencedColumnName = "id"))
    private List<Playlist> playlsits;

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
