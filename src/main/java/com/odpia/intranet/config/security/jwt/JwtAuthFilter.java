package com.odpia.intranet.config.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
	private final JwtTokenProvider tokenProvider;
	private final UserDetailsService uds;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
		String auth = req.getHeader("Authorization");
		if (auth != null && auth.startsWith("Bearer ")) {
			String token = auth.substring(7);
			try {
				var claims = tokenProvider.parse(token).getBody();
				var username = claims.getSubject();
				var user = uds.loadUserByUsername(username);
				var at = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				at.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(at);
			} catch (JwtException e) {
				// 무음 통과 대신 명확히 401 처리
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid/Expired token");
				return;
			}
		}
		chain.doFilter(req, res);
	}
}
