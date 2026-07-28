package com.gabriel_jardim.library_management_backend.reader.mapper;

import com.gabriel_jardim.library_management_backend.reader.Reader;
import com.gabriel_jardim.library_management_backend.reader.dto.ReaderResponse;
import org.springframework.stereotype.Component;

@Component
public class ReaderMapper {

    public ReaderResponse toResponse(Reader reader) {
        return new ReaderResponse(
            reader.getId(),
            reader.getName(),
            reader.getCpf(),
            reader.getEmail(),
            reader.getPhone(),
            reader.getActive()
        );
    }
}
