package com.sampleJpa.ex2.entity;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="MEMBER_TWO")
public class Member extends BaseEntityTwo {
    @Id @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;
    
    @Column(name = "USERNAME")
    private String name;

    // Period
    @Embedded
    private Period period;

    // 주소
    @Embedded
    private Address address;


    // 외래키를 넣어서 사용하는 방식. 객체지향스럽지 않고 관계형 DB에 초점이 맞춰진 설계
    /*
    @Column(name = "TEAM_ID")
    private Long teamId;
    */


    @ManyToOne(fetch = FetchType.LAZY)    // 지연로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD", joinColumns = @JoinColumn(name="MEMBER_ID"))
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="ADDRESS", joinColumns = @JoinColumn(name="MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Member(){
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
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