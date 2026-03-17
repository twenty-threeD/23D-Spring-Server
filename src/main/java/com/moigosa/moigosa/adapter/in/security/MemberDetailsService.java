package com.moigosa.moigosa.adapter.in.security;

import com.moigosa.moigosa.domain.auth.model.Member;
import com.moigosa.moigosa.application.auth.port.out.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(@NonNull
										  String username) throws UsernameNotFoundException {

		Member member = memberRepository.findByUsername(username).orElseThrow(()
				-> new UsernameNotFoundException("사용자정보를 찾을 수 없습니다."));
		return new MemberDetails(member);
	}
}
