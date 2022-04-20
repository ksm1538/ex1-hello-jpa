package com.sampleJpa.ex2.main;

import com.sampleJpa.ex2.entity.Child;
import com.sampleJpa.ex2.entity.Member;
import com.sampleJpa.ex2.entity.Parent;
import com.sampleJpa.ex2.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain_CASCADE {
    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Child c1 = new Child();
            Child c2 = new Child();

            Parent p = new Parent();
            p.addChild(c1);
            p.addChild(c2);

            // CASCADE 사용 안하면, 하나하나 다 넣어줘야함
            /*
            em.persist(p);
            em.persist(c1);
            em.persist(c2);
            */

            // Parent에 CASCADE.ALL 로 설정하였기 때문에 Parent 객체만 넣어도 관련된 엔티티는 자동 영속상태 됨
            em.persist(p);

            em.flush();
            em.clear();

            Parent parent = em.find(Parent.class, p.getId());
            // 컬렉션에서 뺌(고아상태가 되어 DELETE 쿼리가 나감)
            parent.getChilds().remove(0);

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
