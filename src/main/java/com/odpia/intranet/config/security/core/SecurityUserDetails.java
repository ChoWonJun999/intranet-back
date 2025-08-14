package com.odpia.intranet.config.security.core;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.odpia.intranet.user.domain.User;
import com.odpia.intranet.user.domain.UserStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUserDetails implements UserDetails {

	private static final int LOCK_THRESHOLD = 5;

	private final Long id;
	private final String loginId;
	private final String password;
	private final boolean enabled; // status == ACTIVE
	private final boolean accountNonLocked; // 실패 5회 이상이면 잠금
	private final Collection<? extends GrantedAuthority> authorities;

	public static SecurityUserDetails from(User u) {
		// 권한 매핑(단일 role 예시)
		List<GrantedAuthority> auths = List.of(new SimpleGrantedAuthority(u.getRole().name()));

		boolean nonLocked = (u.getLoginFailCount() == null) || (u.getLoginFailCount() < LOCK_THRESHOLD);
		boolean isEnabled = (u.getStatus() == UserStatus.ACTIVE);

		return new SecurityUserDetails(u.getId(), u.getLoginId(), u.getPassword(), isEnabled, nonLocked, auths);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
