package com.sampleJpa.jpql.main;

import com.sampleJpa.jpql.entity.AddressJpql;
import com.sampleJpa.jpql.entity.MemberJpql;
import com.sampleJpa.jpql.entity.MemberJpqlDto;
import com.sampleJpa.jpql.entity.TeamJpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain_Projection {
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

            // ===================== 엔티티 타입 프로젝션 =====================
            // ex1
            List<MemberJpql> resultList = em.createQuery("SELECT m FROM MemberJpql m", MemberJpql.class)
                    .getResultList();
            MemberJpql findMember = resultList.get(0);
            findMember.setAge(20);

            // ex2
            List<TeamJpql> teamList1 = em.createQuery("SELECT m.team FROM MemberJpql m", TeamJpql.class)
                    .getResultList();

            // ex3 (ex2보다 이것을 추천. 개발자입장에서 쿼리만 보고도 조인한다는 것을 알 수 있기 때문)
            List<TeamJpql> teamList2 = em.createQuery("SELECT t FROM MemberJpql m join m.team t", TeamJpql.class)
                    .getResultList();
            // =============================================================


            // ===================== 임베디드 타입 프로젝션 =====================
            List<AddressJpql> addressList = em.createQuery("SELECT o.address FROM Order o", AddressJpql.class)
                    .getResultList();
            // =============================================================

            // ===================== 스칼라 타입 프로젝션 =====================
            // Query & Object[] 타입으로 조회
            List<Object[]> resultList2 = em.createQuery("SELECT DISTINCT m.username, m.age FROM Member m")
                    .getResultList();

            Object[] result2 = resultList2.get(0);
            System.out.println("username = " + result2[0]);
            System.out.println("age = " + result2[1]);

            // new 명령어로 조회
            // 생성자를 이용해서 넣어주기 때문에 생성자가 있어야함(순서와 타입이 일치하는 생성자가 필요힘)
            // 앞에 패키지명을 포함한 전체 클래스명을 입력해야함
            List<MemberJpqlDto> resultList3 = em.createQuery("SELECT DISTINCT new jpql.entity.MemberJpqlDto(m.username, m.age) FROM Member m", MemberJpqlDto.class)
                    .getResultList();

            MemberJpqlDto mjDto = resultList3.get(0);
            System.out.println("username = " + mjDto.getUsername());
            System.out.println("age = " + mjDto.getAge() );
            // =============================================================


            tx.commit();
        } catch(Exception e){
            tx.rollback();

        } finally{
            em.close();
        }

        emf.close();
    }
}
