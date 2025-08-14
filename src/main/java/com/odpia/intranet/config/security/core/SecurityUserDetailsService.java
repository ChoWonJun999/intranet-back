package com.odpia.intranet.config.security.core;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.odpia.intranet.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	// username 파라미터 = loginId로 사용
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = userRepository.findByLoginId(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		return SecurityUserDetails.from(user);
	}
}
