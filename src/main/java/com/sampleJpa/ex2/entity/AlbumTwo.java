package com.sampleJpa.ex2.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("앨범에서 생성됨")
@Table(name="ALBUM_TWO")
public class AlbumTwo extends Items{
    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
