package com.gabriel_jardim.library_management_backend.user.dto;

import com.gabriel_jardim.library_management_backend.user.UserRole;

public record UserResponse(
    Long id,
    String name,
    String email,
    UserRole role,
    Boolean active
) {}
