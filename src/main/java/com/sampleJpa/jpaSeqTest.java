package com.sampleJpa;

import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaSeqTest {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            
            MemberInfo memberInfo1 = new MemberInfo();
            memberInfo1.setMemberName("SEQ 테스트1");
            MemberInfo memberInfo2 = new MemberInfo();
            memberInfo2.setMemberName("SEQ 테스트2");
            MemberInfo memberInfo3 = new MemberInfo();
            memberInfo3.setMemberName("SEQ 테스트3");

            // 처음에 Seq를 가져올 때 넉넉하게 allocationSize(기본값 50개)만큼 한번에 미리 가져옴
            // 그리고 추후에 pk를 할당할 때 DB의 Seq와 다시 통신하지않고 아까 꺼내둔 것을 바로 넣어줌
            em.persist(memberInfo1);
            em.persist(memberInfo2);
            em.persist(memberInfo3);

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
        
    }


}
