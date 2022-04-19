package com.sampleJpa.ex2.entity;

import javax.persistence.*;

@Entity
@Table(name="MEMBER_TWO")
public class Member extends BaseEntityTwo {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    
    @Column(name = "USERNAME")
    private String name;
    
    // 외래키를 넣어서 사용하는 방식. 객체지향스럽지 않고 관계형 DB에 초점이 맞춰진 설계
    /*
    @Column(name = "TEAM_ID")
    private Long teamId;
    */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public Member(){
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}