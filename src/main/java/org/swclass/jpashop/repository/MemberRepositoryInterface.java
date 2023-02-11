package org.swclass.jpashop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.swclass.jpashop.domain.MemberTest;

public interface MemberRepositoryInterface extends JpaRepository<MemberTest, Long> {
    Page<MemberTest> findById(Long id, Pageable pageable);
}
