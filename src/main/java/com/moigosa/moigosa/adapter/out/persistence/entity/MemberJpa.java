package com.moigosa.moigosa.adapter.out.persistence.entity;

import com.moigosa.moigosa.domain.auth.enums.Role;
import com.moigosa.moigosa.domain.auth.model.Member;
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
				.id(id)
				.username(username)
				.password(password)
				.email(username)
				.phone(phone)
				.role(role)
				.build();
	}
}
