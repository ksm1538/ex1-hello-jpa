package com.sampleJpa;

import javax.persistence.*;
import java.util.Date;

/**
 JPA 관련 어노테이션 선언 시,
 javax.persistence로 시작하는 패키지를 임포트할 것!!
 */

// @Entity : 객체라는 의미
// @Table(name = "?") : Table 어노테이션이 없으면 객체의 이름으로 된 테이블에 접근하고, Table 어노테이션이 있으면 Table 어노테이션의 name 테이블에 접근.
// @Column(name = "?") : Table 어노테이션과 마찬가지로 DB 컬럼명과 매핑시켜줌.
// @Id : PK라는 의미

@Entity
public class MemberInfo {
    @Id
    private Long memberId;
    @Column
    private String memberName;

    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    public MemberInfo(){
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
