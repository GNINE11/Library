package com.gabriel_jardim.library_management_backend.reader.dto;

public record ReaderResponse(
    Long id,
    String name,
    String cpf,
    String email,
    String phone,
    Boolean active
) {}
