package org.swclass.jpashop;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.MemberTest;
import org.swclass.jpashop.repository.MemberRepositoryTestInterface;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    // 1. extands JpaRepository<Member,Long> 사용해보기

    @Autowired
//    MemberRepositoryTest memberRepository;
    MemberRepositoryTestInterface memberRepository;

    @Test
    @DisplayName("Member 엔티티 생성 후 저장, 조회")
    @Transactional
    @Rollback(false)
    public void testMember() {
        MemberTest member = new MemberTest();
        member.setUsername("wangi");

        memberRepository.save(member);

        PageRequest pageRequest = PageRequest.of(1, 10);
        System.out.println(memberRepository.findById(member.getId(), pageRequest));

//        assertThat(findMember.getId()).isEqualTo(member.getId());
//        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        assertThat(findMember).isEqualTo(member);
    }
}
