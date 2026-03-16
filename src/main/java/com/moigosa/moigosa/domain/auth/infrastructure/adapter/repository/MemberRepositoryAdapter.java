package com.moigosa.moigosa.domain.auth.infrastructure.adapter.repository;

import com.moigosa.moigosa.domain.auth.domain.model.Member;
import com.moigosa.moigosa.domain.auth.domain.repository.MemberRepository;
import com.moigosa.moigosa.domain.auth.infrastructure.jpa.entity.MemberJpa;
import com.moigosa.moigosa.domain.auth.infrastructure.jpa.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryAdapter implements MemberRepository {

	private final MemberJpaRepository memberJpaRepository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Member save(Member member) {
		return memberJpaRepository
				.save(MemberJpa.from(member))
				.toEntity();
	}

	@Override
	public Optional<Member> findByUsername(String username) {
		return memberJpaRepository
				.findByUsername(username)
				.map(MemberJpa::toEntity);
	}

	@Override
	public boolean existsByUsername(String username) {
		return memberJpaRepository
				.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return memberJpaRepository
				.existsByEmail(email);
	}
}
