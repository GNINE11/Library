package com.gabriel_jardim.library_management_backend.user;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabriel_jardim.library_management_backend.user.dto.ChangeActiveRequest;
import com.gabriel_jardim.library_management_backend.user.dto.ChangePasswordRequest;
import com.gabriel_jardim.library_management_backend.user.dto.CreateUserRequest;
import com.gabriel_jardim.library_management_backend.user.dto.UpdateUserRequest;
import com.gabriel_jardim.library_management_backend.user.dto.UserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request);
    }


    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }


    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }


    @PatchMapping("/{id}/active")
    public UserResponse changeActive(@PathVariable Long id, @Valid @RequestBody ChangeActiveRequest request) {
        return userService.changeActive(id, request);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/change-password")
    public void changePassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, request);
    }


    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id,@Valid @RequestBody UpdateUserRequest request) {
        return userService.update(id, request);
    }
}
