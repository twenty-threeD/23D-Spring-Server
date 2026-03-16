package com.moigosa.moigosa.domain.auth.infrastructure.jpa.repository;

import com.moigosa.moigosa.domain.auth.infrastructure.jpa.entity.MemberJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberJpa, Long> {

	Optional<MemberJpa> findByUsername(String username);

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
