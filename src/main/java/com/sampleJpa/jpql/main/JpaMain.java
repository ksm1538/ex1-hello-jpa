package com.sampleJpa.jpql.main;

import com.sampleJpa.jpql.entity.MemberJpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
            MemberJpql memberJpql = new MemberJpql();
            memberJpql.setUsername("멤버1");
            memberJpql.setAge(10);
            em.persist(memberJpql);

            // TypedQuery : 반환되는 클래스가 명시된 경우 사용.
            // Parameter -> :? 사용 후 추후에 setParameter() 이용
            TypedQuery<MemberJpql> query1 = em.createQuery("SELECT m FROM MemberJpql m WHERE m.username = :username", MemberJpql.class);
            query1.setParameter("username", "멤버1");

            // Query : 반환되는 클래스가 명시되지 않은 경우 사용
            Query query2 = em.createQuery("SELECT m.USERNAME, m.AGE FROM MemberJpql m");

            // getResultList() : 다건 리스트 반환
            List<MemberJpql> resultList = query1.getResultList();

            // getSingResult() : 단일 객체만 반환 (없거나 2개 이상 시, 예외 발생)
            MemberJpql singleResult = query1.getSingleResult();
            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally{
            em.close();
        }

        emf.close();
    }
}
