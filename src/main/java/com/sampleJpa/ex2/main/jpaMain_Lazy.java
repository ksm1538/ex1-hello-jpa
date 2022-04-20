package com.sampleJpa.ex2.main;

import com.sampleJpa.ex2.entity.Member;
import com.sampleJpa.ex2.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain_Lazy {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Team team = new Team();
            team.setName("TEAM_A");
            em.persist(team);

            Member member = new Member();
            member.setName("김멍목");
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member.getId());

            // 지연로딩을 적용한 경우, 지연로딩 객체는 proxy로 생성됨
            System.out.println("m.team = " + m.getTeam().getClass());

            System.out.println("===============");
            System.out.println(m.getTeam().getName());
            System.out.println("===============");



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
