package com.moigosa.moigosa.application.auth.port.out.token;

import com.moigosa.moigosa.domain.auth.enums.Role;

public interface TokenProvider {

	String generateAccessToken(String username, Role role);
	String generateRefreshToken(String username);
	String getUsernameFromToken(String token);

	Role getRole(String token);

	boolean isValidToken(String token);
}
