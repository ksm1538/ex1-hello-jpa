package com.sampleJpa.jpql.main;

import com.sampleJpa.jpql.entity.MemberJpql;
import com.sampleJpa.jpql.entity.TeamJpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_Join {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{ 

            TeamJpql teamJpql = new TeamJpql();
            teamJpql.setName("team");
            em.persist(teamJpql);

            MemberJpql m = new MemberJpql();
            m.setUsername("member");
            m.setAge(10);
            m.setTeamJpql(teamJpql);
            em.persist(m);

            em.flush();
            em.clear();

            // 일반 쿼리
            String query = "SELECT m FROM MemberJpql m INNER JOIN m.teamJpql t";

            // ON절을 사용한 조인 쿼리
            String query_useOn = "SELECT m FROM MemberJpql m LEFT JOIN m.teamJpql t ON t.name = 'team'";

            // 연관관계 없는 엔티티 외부 조인
            String query_free = "SELECT m FROM MemberJpql m LEFT JOIN Team t ON m.username = t.name";
            List<MemberJpql> list = em.createQuery(query, MemberJpql.class)
                    .getResultList();

            for(MemberJpql memberJpql : list){
                System.out.println(memberJpql.toString());
            }

            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally{
            em.close();
        }

        emf.close();
    }
}
