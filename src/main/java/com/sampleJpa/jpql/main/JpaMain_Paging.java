package com.sampleJpa.jpql.main;

import com.sampleJpa.jpql.entity.AddressJpql;
import com.sampleJpa.jpql.entity.MemberJpql;
import com.sampleJpa.jpql.entity.MemberJpqlDto;
import com.sampleJpa.jpql.entity.TeamJpql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_Paging {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{

            for(int i=0; i<50; i++){
                MemberJpql m = new MemberJpql();
                m.setUsername("member"+i);
                m.setAge(10 + i);

                em.persist(m);
            }

            em.flush();
            em.clear();
            // 페이징
            List<MemberJpql> list = em.createQuery("SELECT m FROM MemberJpql m ORDER BY m.age DESC", MemberJpql.class)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList();

            for(MemberJpql m : list){
                System.out.println(m.toString());
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
