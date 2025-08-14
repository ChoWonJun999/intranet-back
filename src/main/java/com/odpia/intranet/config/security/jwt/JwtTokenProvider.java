package com.odpia.intranet.config.security.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

	@Value("${security.jwt.secret}")
	private String secret;
	@Value("${security.jwt.access-exp-min:30}")
	private long accessExpMin;
	@Value("${security.jwt.refresh-exp-day:7}")
	private long refreshExpDay;

	private Key key() {
		return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	public String createAccessToken(String subject, Collection<String> roles) {
		Instant now = Instant.now();
		return Jwts.builder()
		        .setSubject(subject)
		        .claim("roles", roles)
		        .setIssuedAt(Date.from(now))
		        .setExpiration(Date.from(now.plus(Duration.ofMinutes(accessExpMin))))
		        .signWith(key(), SignatureAlgorithm.HS256)
		        .compact();
	}

	public String createRefreshToken(String subject) {
		Instant now = Instant.now();
		return Jwts.builder()
		        .setSubject(subject)
		        .setIssuedAt(Date.from(now))
		        .setExpiration(Date.from(now.plus(Duration.ofDays(refreshExpDay))))
		        .signWith(key(), SignatureAlgorithm.HS256)
		        .compact();
	}

	public Jws<Claims> parse(String token) {
		return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
	}
}
