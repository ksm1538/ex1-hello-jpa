package com.sampleJpa.practice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaUpdateMain {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();

        try{
            // PK로 MemberInfo 테이블 조회하기.
            // PK는 1L
            MemberInfo memberInfo = em.find(MemberInfo.class, 1L);
            System.out.println("아이디 : " + memberInfo.getMemberId());
            System.out.println("이름 : " + memberInfo.getMemberName());

            // 멤버의 이름을 수정
            // 트랜잭션 내부에서 수정 시, 커밋할 때 변경된 값을 감지해서 알아서 수정함
            memberInfo.setMemberName("changed Name");

            // 커밋
            et.commit();
        } catch(Exception e){
            // 오류 시 롤백
            et.rollback();
        } finally {
            // 작업 종료 할 때, EntityManager 닫는 것 잊지않기!
            em.close();
        }

        emf.close();

    }
}
