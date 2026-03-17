package com.moigosa.moigosa.adapter.out.token;

import com.moigosa.moigosa.application.auth.port.out.token.TokenProvider;
import com.moigosa.moigosa.domain.auth.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtProvider implements TokenProvider {

	private final SecretKey secretKey;
	private final Long accessTokenExpiration;
	private final Long refreshTokenExpiration;

	public JwtProvider(@Value("${spring.jwt.secret}") String secret,
					   @Value("${spring.jwt.refreshTokenExpiration}") Long refreshTokenExpiration,
					   @Value("${spring.jwt.accessTokenExpiration}") Long accessTokenExpiration) {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.accessTokenExpiration = accessTokenExpiration;
		this.refreshTokenExpiration = refreshTokenExpiration;
	}

	/**
	 *
	 * @param username 사용자명
	 * @param role 사용자 권한(BASIC, STANDARD, PRO)
	 * @return accessToken
	 */
	@Override
	public String generateAccessToken(String username, Role role) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + accessTokenExpiration);

		return Jwts.builder()
				.subject(username)

				.claim("role", role)
				.claim("tokenType", "accessToken")

				.issuedAt(now)
				.expiration(expiration)
				.signWith(secretKey)

				.compact();
	}

	@Override
	public String generateRefreshToken(String username) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + refreshTokenExpiration);

		return Jwts.builder()
				.subject(username)

				.claim("tokenType", "refreshToken")

				.issuedAt(now)
				.expiration(expiration)
				.signWith(secretKey)

				.compact();
	}

	@Override
	public String getUsernameFromToken(String token) {
		return getClaims(token).getSubject();
	}

	@Override
	public Role getRole(String token) {
		return Role.valueOf(getClaims(token).get("role", String.class));
	}

	@Override
	public boolean isValidToken(String token) {
		try {
			getClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	private Claims getClaims(String token) {
		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
}
