package com.sampleJpa.jpql.entity;

import javax.persistence.*;

@Entity
@Table(name="MEMBER_JPQL")
@NamedQuery(
        name = "MemberJpql.findByUsername",
        query="select m from MemberJpql m where m.username = :username")
public class MemberJpql {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private TeamJpql teamJpql;

    public void changeTeam(TeamJpql teamJpql){
        this.setTeamJpql(teamJpql);
        teamJpql.getMembers().add(this);

    }
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

    public TeamJpql getTeamJpql() {
        return teamJpql;
    }

    public void setTeamJpql(TeamJpql teamJpql) {
        this.teamJpql = teamJpql;
    }

    @Override
    public String toString() {
        return "MemberJpql{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
