package com.sampleJpa.ex2.main;

import com.sampleJpa.ex2.entity.Member;
import com.sampleJpa.ex2.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain_Proxy {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member = new Member();
            member.setName("김멍목");

            em.persist(member);

            em.flush();
            em.clear();

            Member refMember = em.getReference(Member.class, member.getId());
            System.out.println("refMember = "+refMember.getClass());

            // 영속성컨텍스트에서 값을 받기 전에, 준영속상태가 되면 추후에 문제 발생
            em.detach(refMember);

            // 준영속상태에서 해당 프록시를 사용하려고하니 문제 발생
            System.out.println("refMember = "+refMember.getName());


            tx.commit();
        } catch(Exception e){
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
