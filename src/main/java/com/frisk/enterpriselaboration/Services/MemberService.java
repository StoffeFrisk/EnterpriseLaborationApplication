package com.frisk.enterpriselaboration.Services;

import com.frisk.enterpriselaboration.Enteties.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    ResponseEntity<List<Member>> getAllMembers();

    ResponseEntity<?> getMemberById(Long id);

    ResponseEntity<?> addMember(Member member);

    ResponseEntity<String> deleteMember(Long id);

    ResponseEntity<?> updateMember(Member member);
}
