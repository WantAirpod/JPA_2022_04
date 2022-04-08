package com.cjy9249.orderShop.domain.repository.member;

import com.cjy9249.orderShop.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
}
