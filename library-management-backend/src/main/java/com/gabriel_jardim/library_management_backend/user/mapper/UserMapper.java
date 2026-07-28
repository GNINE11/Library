package com.gabriel_jardim.library_management_backend.user.mapper;

import com.gabriel_jardim.library_management_backend.user.User;
import com.gabriel_jardim.library_management_backend.user.dto.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getActive()
        );
    }
}
