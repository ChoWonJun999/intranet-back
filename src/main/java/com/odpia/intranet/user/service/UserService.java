package com.odpia.intranet.user.service;

import com.odpia.intranet.user.domain.User;
import com.odpia.intranet.user.domain.UserStatus;
import com.odpia.intranet.user.dto.*;
import com.odpia.intranet.user.exception.NotFoundException;
import com.odpia.intranet.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public UserResponse create(UserCreateRequest req) {
		if (userRepository.existsByEmail(req.email())) {
			throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + req.email());
		}
		User user = User.builder()
				.email(req.email())
				.name(req.name())
				.status(req.status() == null ? UserStatus.ACTIVE : req.status())
				.createdBy(req.createdBy())
				.build();
		return toResponse(userRepository.save(user));
	}

	@Transactional(readOnly = true)
	public UserResponse get(Long id) {
		return toResponse(find(id));
	}

	@Transactional(readOnly = true)
	public List<UserResponse> list() {
		return userRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Transactional
	public UserResponse update(Long id, UserUpdateRequest req) {
		User user = find(id);
		user.setName(req.name());
		if (req.status() != null)
			user.setStatus(req.status());
		return toResponse(user);
	}

	@Transactional
	public void delete(Long id) {
		User user = find(id);
		userRepository.delete(user);
	}

	private User find(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다. id=" + id));
	}

	private UserResponse toResponse(User u) {
		return new UserResponse(u.getId(), u.getEmail(), u.getName(), u.getStatus(), u.getCreatedAt(),
				u.getUpdatedAt());
	}
}
