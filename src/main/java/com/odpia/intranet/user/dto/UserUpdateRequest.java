package com.odpia.intranet.user.dto;

import com.odpia.intranet.user.domain.UserStatus;
import jakarta.validation.constraints.*;

public record UserUpdateRequest(
        @NotBlank @Size(max = 50) String name,
        UserStatus status) {
}
