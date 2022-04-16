package com.sampleJpa.practice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaPersistenceContext {
    public static void main(String[] args){

        // 아래 들어가는 인자는 resources/META-INF/persistence.xml의 persistence-unit name을 넣어주면 된다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManagerFactory를 이용해 EntityManager 생성
        EntityManager em = emf.createEntityManager();

        // EntityManager 를 통해서 트랜잭션 가져오기
        EntityTransaction tx = em.getTransaction();

        // 트랜잭션 시작
        tx.begin();

        try {
            // 비영속 상태 (JPA를 이용해서 생성하거나 수정한 것이 아님)
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.setMemberId(2L);
            memberInfo.setMemberName("홍길동");

            // 영속 상태
            // 영속성 컨텍스트에 넣어줌. (이 때, INSERT를 하는 것이 아님) -> 쓰기 지연
            em.persist(memberInfo);

            // 영속성 컨텍스트에 넣었거나, DB에서 한 번 조회한 데이터는 1차 캐시에 저장을 했기 때문에 DB에서 가져오지 않고 1차 캐시에서 가져옴
            MemberInfo memberInfo1 = em.find(MemberInfo.class, 5L);
            MemberInfo memberInfo2 = em.find(MemberInfo.class, 5L);

            // 영속 엔티티의 동일성 보장 (동일 트랜잭션 내에서)
            // is Equals? true
            System.out.println("is Equals? " + (memberInfo1 == memberInfo2));

            // 영속성 컨텍스트에 있는 것을 쿼리로 변환
            // 쓰기 지연으로 커밋할 때 변경된 내용을 한 번에 쿼리로 변환하여 조작
            tx.commit();
        // 오류 발생시 롤백
        } catch(Exception e){
            tx.rollback();

        // 정상적으로 커밋 or 오류 발생의 두 경우 다 entityManager 종료
        } finally {
            em.close();
        }

        emf.close();




    }
}
