package com.odpia.intranet.user.dto;

import com.odpia.intranet.user.domain.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(@Email @NotBlank String email, @NotBlank @Size(max = 50) String userName,
        UserStatus status, Long createdBy) {
}
