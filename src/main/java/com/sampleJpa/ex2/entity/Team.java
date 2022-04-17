package com.sampleJpa.ex2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TEAM_TWO")
public class Team {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    // Team에는 많은 Member가 있기 때문에 OneToMany
    // mappedBy로 매핑
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
    public Team(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
