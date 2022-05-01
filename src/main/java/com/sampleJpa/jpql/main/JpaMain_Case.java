package com.sampleJpa.jpql.main;

import com.sampleJpa.jpql.entity.MemberJpql;
import com.sampleJpa.jpql.entity.TeamJpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_Case {
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

            // 기본 CASE 식
            String query =
                    "SELECT " +
                    "   CASE WHEN m.age >= 10 then '학생요금'" +
                    "        WHEN m.age <= 60 then '경로요금'" +
                    "        ELSE '일반요금'" +
                    "   END" +
                    "FROM memberJpql m";

            // COALESCE 식
            String query_coalesce = "SELECT COALESCE(m.username, '이름 없는 회원') FROM MemberJpql m";

            List<String> result = em.createQuery(query, String.class).getResultList();

            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally{
            em.close();
        }

        emf.close();
    }
}
