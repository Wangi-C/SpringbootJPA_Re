package org.swclass.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.swclass.jpashop.domain.MemberTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    EntityManager em;

    public Long save(MemberTest member) {
        em.persist(member);
        return member.getId();
    }

    public MemberTest find(Long id) {
        return em.find(MemberTest.class, id);
    }
}
