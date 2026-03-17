package com.moigosa.moigosa.adapter.out.persistence.repository;

import com.moigosa.moigosa.adapter.out.persistence.entity.MemberJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberJpa, Long> {

	Optional<MemberJpa> findByUsername(String username);

	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
