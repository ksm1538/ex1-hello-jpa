package com.sampleJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {

    // JPA의 장점
    /**
     1. 쓰기 지연
     2. 동일성 보장
     3. 지연 로딩
     4. 변경 감지
     5. 1차 캐시
     */
    
    
    public static void main(String[] args){
        // 아래 들어가는 인자는 resources/META-INF/persistence.xml의 persistence-unit name을 넣어주면 된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // em 이 실질적으로 DB조작하는 기능이라고 생각하면 된다.
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 가져오기
        EntityTransaction et = em.getTransaction();

        // 트랜잭션 시작
        et.begin();

        try{
            // memberInfo 객체에다가 memberId와 memberName에 값을 설정
            // Id에 값을 넣지 않고 실행 시, 오류 발생함 (PK는 값이 있어야 하기 때문)
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setMemberId(1L);
            memberInfo.setMemberName("firstName");
            em.persist(memberInfo);

            // 트랜잭션 커밋
            et.commit();
        } catch (Exception e){
            // 오류 발생 시, 트랜잭션 롤백
            et.rollback();
        } finally {
            // 끝나면 EntityManager 꼭 닫아주기
            em.close();
        }

        emf.close();
    }
}
