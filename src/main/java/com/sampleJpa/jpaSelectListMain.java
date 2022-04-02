package com.sampleJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaSelectListMain {
    public static void main(String[] args){
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

       EntityManager em = emf.createEntityManager();
       EntityTransaction et = em.getTransaction();

       et.begin();

       try{
           // MemberInfo 리스트 조회 방법
           // JPQL을 이용함.
           // 쿼리를 보면, 우리가 알고있는 일반적인 쿼리가 아닌 것을 알 수 있음.
           // 멤버인포를 다 가져오라는 뜻.
           //List<MemberInfo> memberInfoList = em.createQuery("select m from MemberInfo as m", MemberInfo.class).getResultList();


           // .setFirstResult  : Rownum이 1번 데이터부터 (0부터 존재함)
           // .setMaxResults : 데이터를 2개 조회
           // 이렇게, setFirstResult나, setMaxResults를 사용하면 각자 설정해둔 DBMS 방언이 알아서 매핑되서 쿼리로 생성된다.
           List<MemberInfo> memberInfoList = em.createQuery("select m from MemberInfo as m", MemberInfo.class)
                   .setFirstResult(1)
                   .setMaxResults(2)
                   .getResultList();

           for (MemberInfo memberInfo : memberInfoList) {
               System.out.println("아이디 : " + memberInfo.getMemberId() + " / 이름 : " + memberInfo.getMemberName());
           }
           // 정상작동 시, 커밋
           et.commit();

       // 오류 발생 시, 롤백
       } catch(Exception e){
           et.rollback();
       // 성공 / 오류 상관없이 마지막에 거치는 영역
       } finally {
           // EntityManager를 닫아준다.
           em.close();
       }

       emf.close();
    }
}
