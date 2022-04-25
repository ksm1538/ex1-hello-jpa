package com.sampleJpa.ex2.main;

import com.sampleJpa.ex2.entity.Address;
import com.sampleJpa.ex2.entity.Member;
import com.sampleJpa.ex2.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain_Collection {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member = new Member();
            member.setName("member1");
            member.setAddress(new Address("city", "street",  "1223" ));

            // 컬렉션 값 타입 사용
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("라면");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new Address("old_city1", "old_street1", "1111"));
            member.getAddressHistory().add(new Address("old_city2", "old_street2", "1112"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("========== MODIFY ==========");
            Member find = em.find(Member.class, member.getId());

            // set이 아닌, 통으로 갈아끼는 방식
            find.setAddress(new Address("new City", "new street", "7777"));

            // 치킨 > 오리고기 (UPDATE가 아닌, DELETE 후 REMOVE)
            find.getFavoriteFoods().remove("치킨");
            find.getFavoriteFoods().add("오리고기");

            find.getAddressHistory().remove(new Address("old_city1", "old_street1", "1111"));
            find.getAddressHistory().add(new Address("old_city5", "old_street5", "115"));
            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
