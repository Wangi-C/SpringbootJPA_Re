package org.swclass.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.Member;
import org.swclass.jpashop.domain.MemberTest;
import org.swclass.jpashop.repository.MemberRepositoryTestInterface;
import org.swclass.jpashop.repository.ShopMemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberRepositoryTest {
    // 1. extands JpaRepository<Member,Long> 사용해보기

    @Autowired
//    MemberRepositoryTest memberRepository;
//    MemberRepositoryTestInterface memberRepository;
    ShopMemberRepository memberRepository;

//    @Test
//    @DisplayName("Member 엔티티 생성 후 저장, 조회")
//    @Transactional
//    @Rollback(false)
//    public void testMember() {
//        Member member = new Member();
//        member.setName("wangi");
//
//        memberRepository.save(member);
//
//        PageRequest pageRequest = PageRequest.of(1, 10);
//        System.out.println(memberRepository.findOne(member.getId(), pageRequest));
//
//        assertThat(findMember.getId()).isEqualTo(member.getId());
//        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        assertThat(findMember).isEqualTo(member);
//    }

//    @Test
    @DisplayName("주문도메인 Test")
    @Transactional
    public void testMemberDomain() {
        Member member = new Member();
        member.setName("jieun");
        Long memberId = memberRepository.save(member);

        Member findMember = memberRepository.findOne(memberId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }
}
