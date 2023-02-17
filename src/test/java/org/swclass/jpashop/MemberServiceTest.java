package org.swclass.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.Member;
import org.swclass.jpashop.repository.ShopMemberRepository;
import org.swclass.jpashop.service.MemberService;

import static org.assertj.core.api.Fail.fail;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    ShopMemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void joinTest() {
        Member member = new Member();
        member.setName("wangi");

        Long memberId = memberService.join(member);

        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(memberId));
    }

    @Test(expected = IllegalStateException.class)
    public void exceptionTest() {
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        memberService.join(member2);

        fail("예외가 발생");
    }
}
