package com.sampleJpa.ex2.main;

import com.sampleJpa.ex2.entity.AddressTwo;
import com.sampleJpa.ex2.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain_Embedded {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            AddressTwo address = new AddressTwo("city", "street", "100000");
            Member mem1 = new Member();
            mem1.setName("네임");
            mem1.setAddress(address);
            em.persist(mem1);

            Member mem2 = new Member();
            mem2.setName("네임2");
            mem2.setAddress(address);
            em.persist(mem2);

            // mem1의 city를 바꾸고싶어함.
            // 하지만 mem2도 바뀌어짐
            // 임베디드 타입을 여러 엔티티에서 공유하면 위험.
            // 이걸 막기위해선 새로운 객체를 생성해서 mem2 에 넣어야함(깊은복사로)
            mem1.getAddress().setCity("changed City");

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
