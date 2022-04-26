package com.sampleJpa.jpql.entity;

import javax.persistence.*;

@Entity
@Table(name="MEMBER_JPQL")
public class MemberJpql {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private TeamJpql team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TeamJpql getTeam() {
        return team;
    }

    public void setTeam(TeamJpql team) {
        this.team = team;
    }
}
