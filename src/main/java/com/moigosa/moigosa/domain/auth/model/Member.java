package com.moigosa.moigosa.domain.auth.model;

import com.moigosa.moigosa.domain.auth.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

	private Long id;

	private String username; // 사용자명

	private String password; // 비밀번호

	private String email; // 이메일

	private String phone; // 전화번호

	private Role role;
}
