package com.sampleJpa.ex2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="MOVIE_TWO")
public class MovieTwo extends Items{
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
