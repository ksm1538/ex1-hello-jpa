package com.sampleJpa.UUID_Test.main;

import com.sampleJpa.UUID_Test.entity.Uuid;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UUID_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Uuid uuid = new Uuid(null, "test", "pw");

            em.persist(uuid);

            em.flush();
            em.clear();

            Uuid find = em.find(Uuid.class, uuid.getUserSeq());

            System.out.printf("find : " + find.toString());

            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }
        emf.close();
    }

}
