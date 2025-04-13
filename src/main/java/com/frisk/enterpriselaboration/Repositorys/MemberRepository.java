package com.frisk.enterpriselaboration.Repositorys;

import com.frisk.enterpriselaboration.Enteties.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
