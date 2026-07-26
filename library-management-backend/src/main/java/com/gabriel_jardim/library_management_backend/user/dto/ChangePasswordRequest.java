package com.gabriel_jardim.library_management_backend.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(

    @NotBlank(message = "A senha atual é obrigatória.")
    @Size(min = 8, max = 72, message = "A senha deve conter entre 8 e 72 caracteres.")
    String currentPassword,
    
    @NotBlank(message = "A nova senha é obrigatória.")
    @Size(min = 8, max = 72, message = "A nova senha deve conter entre 8 e 72 caracteres.")
    String newPassword,

    @NotBlank(message = "A confirmação da nova senha é obrigatória.")
    @Size(min = 8, max = 72, message = "A confirmação da senha deve conter entre 8 e 72 caracteres.")
    String confirmNewPassword
) {}
