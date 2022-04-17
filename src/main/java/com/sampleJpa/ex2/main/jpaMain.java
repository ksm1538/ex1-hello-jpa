package com.sampleJpa.ex2.main;

import com.sampleJpa.ex2.entity.Member;
import com.sampleJpa.ex2.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
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
            // Team의 외래키를 직접 넣어주는 방식 (객체지향스럽지 못함)
            //member.setTeamId(team.getId());
            
            // 연관관계 어노테이션을 이용해서 간편하게 사용 가능
            member.setTeam(team);

            em.persist(member);

            // flush와 clear를 통해 미리 쿼리를 날리고 영속성 컨텍스트를 비움. (추후에 1차캐시에서 가져오는 것이 아닌 쿼리에서 가져오게 하기 위함)
            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();;
            for(Member m : members){
                System.out.println("member_name : " + m.getName());
            }

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
