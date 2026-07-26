package com.gabriel_jardim.library_management_backend.common.exception;

public class ConflictException extends RuntimeException{
    
    public ConflictException(String message) {
        super(message);
    }
}
