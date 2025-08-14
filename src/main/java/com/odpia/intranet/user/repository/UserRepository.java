package com.odpia.intranet.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.odpia.intranet.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
