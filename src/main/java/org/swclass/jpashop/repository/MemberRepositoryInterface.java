package org.swclass.jpashop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.swclass.jpashop.domain.Member;

public interface MemberRepositoryInterface extends JpaRepository<Member, Long> {
    Page<Member> findById(Long id, Pageable pageable);
}
