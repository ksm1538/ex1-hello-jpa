package com.sampleJpa.jpql.main;

import com.sampleJpa.jpql.entity.MemberJpql;
import com.sampleJpa.jpql.entity.TeamJpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_NamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{ 

            TeamJpql teamJpql1 = new TeamJpql();
            teamJpql1.setName("teamA");
            em.persist(teamJpql1);

            TeamJpql teamJpql2 = new TeamJpql();
            teamJpql2.setName("teamB");
            em.persist(teamJpql2);

            MemberJpql m1 = new MemberJpql();
            m1.setUsername("member1");
            m1.setAge(10);
            m1.setTeamJpql(teamJpql1);
            em.persist(m1);

            MemberJpql m2 = new MemberJpql();
            m2.setUsername("member2");
            m2.setAge(20);
            m2.setTeamJpql(teamJpql2);
            em.persist(m2);

            em.flush();
            em.clear();

            List<MemberJpql> resultList = em.createNamedQuery("MemberJpql.findByUsername", MemberJpql.class)
                                            .setParameter("username","회원1")
                                            .getResultList();

            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally{
            em.close();
        }

        emf.close();
    }
}
