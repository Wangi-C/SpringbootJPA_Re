package org.swclass.jpashop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.swclass.jpashop.domain.Address;
import org.swclass.jpashop.domain.Member;
import org.swclass.jpashop.domain.form.MemberForm;
import org.swclass.jpashop.service.MemberService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping(value = "/member/{id}/edit")
    public String editForm(@PathVariable("id") Long memberId, Model model) {
        Member member = memberService.findMember(memberId);
        Address address = member.getAddress();

        MemberForm memberForm = new MemberForm();
        memberForm.setMemberId(member.getId());
        memberForm.setName(member.getName());
        memberForm.setCity(address.getCity());
        memberForm.setStreet(address.getStreet());
        memberForm.setZipcode(address.getZipcode());

        model.addAttribute("member", memberForm);

        return "/members/editForm";
    }

    @PostMapping(value = "/member/{id}/edit")
    public String edit(@PathVariable("id") Long memberId, @ModelAttribute("form") MemberForm form) {
        memberService.editInfo(form);

        return "redirect:/members";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
