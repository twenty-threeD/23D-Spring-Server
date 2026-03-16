package com.moigosa.moigosa.domain.auth.infrastructure.jpa.entity;

import com.moigosa.moigosa.domain.auth.domain.enums.Role;
import com.moigosa.moigosa.domain.auth.domain.model.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	private String email;

	private String phone;

	@Enumerated(EnumType.STRING)
	private Role role;

	public static MemberJpa from(Member member) {
		return new MemberJpa(
				member.getId(),
				member.getUsername(),
				member.getPassword(),
				member.getEmail(),
				member.getPhone(),
				member.getRole()
		);
	}

	public Member toEntity() {
		return Member.builder()
				.id(this.id)
				.username(this.username)
				.password(this.password)
				.email(this.username)
				.phone(this.phone)
				.role(role)
				.build();
	}
}
