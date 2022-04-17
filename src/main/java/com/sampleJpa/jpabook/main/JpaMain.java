package com.sampleJpa.jpabook.main;

import com.sampleJpa.jpabook.entity.Order;
import com.sampleJpa.jpabook.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            Order order = new Order();
            order.addOrderItem(new OrderItem());


            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally{
            em.close();
        }

        emf.close();
    }
}
