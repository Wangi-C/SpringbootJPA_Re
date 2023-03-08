package org.swclass.jpashop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.swclass.jpashop.domain.Address;
import org.swclass.jpashop.domain.Member;
import org.swclass.jpashop.domain.form.MemberForm;
import org.swclass.jpashop.repository.MemberRepository;
import org.swclass.jpashop.repository.ShopMemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final ShopMemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void editInfo(MemberForm memberForm) {
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = memberRepository.findOne(memberForm.getMemberId());
        member.setAddress(address);
        member.setName(memberForm.getName());
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.findOne(id);
    }
}
