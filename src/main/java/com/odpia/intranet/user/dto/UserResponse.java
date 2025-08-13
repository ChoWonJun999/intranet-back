package com.odpia.intranet.user.dto;

import com.odpia.intranet.user.domain.UserStatus;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        String name,
        UserStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
