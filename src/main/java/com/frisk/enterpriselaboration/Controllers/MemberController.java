package com.frisk.enterpriselaboration.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.frisk.enterpriselaboration.Enteties.Member;
import com.frisk.enterpriselaboration.Services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable("id") Long id) {
        return memberService.getMemberById(id);
    }

    @PutMapping("/updatemember/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody Member member) {
        member.setId(id);
        return memberService.updateMember(member);
    }

    @PostMapping("/addmember")
    public ResponseEntity<?> addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }

    @DeleteMapping("/deletemember/{id}")
    public ResponseEntity<String> deleteMemberById(@PathVariable("id") Long id) {
        return memberService.deleteMember(id);
    }

    @GetMapping("/deletemember")
    public String deleteMemberPage(Model model) {
        List<Member> members = memberService.getAllMembers().getBody();
        model.addAttribute("members", members);
        return "deletemember";
    }

    @PostMapping("/deletemember/{id}")
    public String deleteMemberFromWeb(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/deletemember";
    }
}
