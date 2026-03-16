package com.moigosa.moigosa.domain.auth.domain.repository;

import com.moigosa.moigosa.domain.auth.domain.model.Member;

import java.util.Optional;

public interface MemberRepository {

	Member save(Member member);

	Optional<Member> findByUsername(String username);

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}