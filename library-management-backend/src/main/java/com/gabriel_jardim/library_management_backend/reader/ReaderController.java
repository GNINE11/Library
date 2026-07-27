package com.gabriel_jardim.library_management_backend.reader;

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

import com.gabriel_jardim.library_management_backend.reader.dto.ChangeActiveRequest;
import com.gabriel_jardim.library_management_backend.reader.dto.ReaderRequest;
import com.gabriel_jardim.library_management_backend.reader.dto.ReaderResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {
    
    private final ReaderService readerService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReaderResponse create(@Valid @RequestBody ReaderRequest request) {
        return readerService.create(request);
    }


    @GetMapping
    public List<ReaderResponse> findAll() {
        return readerService.findAll();
    }


    @GetMapping("/{id}")
    public ReaderResponse findById(@PathVariable Long id) {
        return readerService.findById(id);
    }


    @PatchMapping("/{id}/active")
    public ReaderResponse changeActive(@PathVariable Long id, @Valid @RequestBody ChangeActiveRequest request) {
        return readerService.changeActive(id, request);
    }


    @PutMapping("/{id}")
    public ReaderResponse update(@PathVariable Long id, @Valid @RequestBody ReaderRequest request) {
        return readerService.update(id, request);
    }
}
