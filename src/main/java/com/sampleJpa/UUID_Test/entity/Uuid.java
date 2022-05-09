package com.sampleJpa.UUID_Test.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Uuid {
    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID userSeq;

    private String userId;
    private String userPw;

    public Uuid() {
    }

    public Uuid(UUID userSeq, String userId, String userPw) {
        this.userSeq = userSeq;
        this.userId = userId;
        this.userPw = userPw;
    }

    public UUID getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(UUID userSeq) {
        this.userSeq = userSeq;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    @Override
    public String toString() {
        return "Uuid{" +
                "userSeq=" + userSeq +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                '}';
    }
}
