package com.moigosa.moigosa.global.jwt;

import com.moigosa.moigosa.domain.auth.domain.model.Member;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record MemberDetails(Member member) implements UserDetails {

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(member.getRole().name()));
	}

	@Override
	public @Nullable String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getUsername();
	}
}
