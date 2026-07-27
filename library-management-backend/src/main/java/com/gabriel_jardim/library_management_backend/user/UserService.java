package com.gabriel_jardim.library_management_backend.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel_jardim.library_management_backend.common.exception.BusinessRuleException;
import com.gabriel_jardim.library_management_backend.common.exception.ConflictException;
import com.gabriel_jardim.library_management_backend.common.exception.ResourceNotFoundException;
import com.gabriel_jardim.library_management_backend.loan.LoanRepository;
import com.gabriel_jardim.library_management_backend.user.dto.ChangeActiveRequest;
import com.gabriel_jardim.library_management_backend.user.dto.ChangePasswordRequest;
import com.gabriel_jardim.library_management_backend.user.dto.CreateUserRequest;
import com.gabriel_jardim.library_management_backend.user.dto.UpdateUserRequest;
import com.gabriel_jardim.library_management_backend.user.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final PasswordEncoder passwordEncoder;


    private UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getActive()
        );
    }


    private User findEntityById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }


    @Transactional
    public UserResponse create(CreateUserRequest request) {

        // TODO: usuário deve estar logado e ter role = admin

        if(userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email já cadastrado.");
        }

        User user = User.builder()
            .name(request.name())
            .email(request.email())
            .passwordHash(passwordEncoder.encode(request.password()))
            .role(request.role())
            .active(true)
            .build();

        return toResponse(userRepository.save(user));
    }


    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        // TODO: usuário deve estar logado e ter role = admin

        List<User> users = userRepository.findAll();
        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {
            UserResponse response = toResponse(user);
            responses.add(response);
        }

        return responses;
    }


    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        // TODO: usuário deve estar logado e ter role = admin
        return toResponse(findEntityById(id));
    }


    @Transactional
    public void changePassword(Long id, ChangePasswordRequest request) {
        // TODO: usuário deve estar logado

        User user = findEntityById(id);

        if(!passwordEncoder.matches(request.currentPassword(), user.getPasswordHash())) {
            throw new BusinessRuleException("A senha atual está incorreta");
        }

        if (!request.newPassword().equals(request.confirmNewPassword())) {
            throw new BusinessRuleException("A nova senha e a confirmação da senha não coincidem.");
        }

        user.setPasswordHash(passwordEncoder.encode(request.newPassword()));
    }


    @Transactional
    public UserResponse update(Long id, UpdateUserRequest request) {
        // TODO: usuário deve estar logado e ter role = admin

        User user = findEntityById(id);

        if (userRepository.existsByEmailAndIdNot(request.email(), id)) {
            throw new ConflictException("Já exite um usuário com esse e-mail");
        }

        user.setName(request.name());
        user.setEmail(request.email());
        user.setRole(request.role());

        return toResponse(user);
    }


    @Transactional
    public UserResponse changeActive(Long id, ChangeActiveRequest request){
        // TODO: usuário deve estar logado e ter role = admin

        User user = findEntityById(id);

        if (!request.active() && loanRepository.existsByLoanedByIdAndReturnedByIsNull(id)) {
            throw new BusinessRuleException("Não é possível desativar um usuário com empréstimos pendentes");
        }

        user.setActive(request.active());
        return toResponse(user);
    }
}
