package com.gabriel_jardim.library_management_backend.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabriel_jardim.library_management_backend.common.exception.BusinessRuleException;
import com.gabriel_jardim.library_management_backend.common.exception.ConflictException;
import com.gabriel_jardim.library_management_backend.common.exception.ResourceNotFoundException;
import com.gabriel_jardim.library_management_backend.loan.LoanRepository;
import com.gabriel_jardim.library_management_backend.loan.LoanStatus;
import com.gabriel_jardim.library_management_backend.reader.dto.ChangeActiveRequest;
import com.gabriel_jardim.library_management_backend.reader.dto.ReaderRequest;
import com.gabriel_jardim.library_management_backend.reader.dto.ReaderResponse;
import com.gabriel_jardim.library_management_backend.reader.mapper.ReaderMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReaderService {
    
    private final ReaderRepository readerRepository;
    private final LoanRepository loanRepository;
    private final ReaderMapper readerMapper;

    @Transactional(readOnly = true)
    private Reader findEntityById(Long id) {
        return readerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Leitor não encontrado."));
    }

    @Transactional
    public ReaderResponse create(ReaderRequest request) {

        // TOOD: deve estar logado
        
        if (readerRepository.existsByCpf(request.cpf())) {
            throw new ConflictException("Leitor já cadastrado.");
        }

        Reader reader = Reader.builder()
            .name(request.name())
            .email(request.email())
            .cpf(request.cpf())
            .phone(request.phone())
            .active(true)
            .build();


        return readerMapper.toResponse(readerRepository.save(reader));
    }

    @Transactional(readOnly = true)
    public List<ReaderResponse> findAll() {

        // TODO: deve estar logado

        List<Reader> readers = readerRepository.findAll();
        List<ReaderResponse> responses = new ArrayList<>();

        for (Reader reader : readers) {
            ReaderResponse response = readerMapper.toResponse(reader);
            responses.add(response);
        }

        return responses;
    }


    @Transactional(readOnly = true)
    public ReaderResponse findById(Long id) {

        // TODO: deve estar logado
        return readerMapper.toResponse(findEntityById(id));
    }


    @Transactional
    public ReaderResponse update(Long id, ReaderRequest request) {

        // TODO: deve estar logado
        Reader reader = findEntityById(id);

        if (readerRepository.existsByCpfAndIdNot(request.cpf(), id)) {
            throw new ConflictException("Já existe um leitor com esse CPF.");
        }

        reader.setName(request.name());
        reader.setCpf(request.cpf());
        reader.setEmail(request.email());
        reader.setPhone(request.phone());

        return readerMapper.toResponse(reader);
    }

    
    @Transactional
    public ReaderResponse changeActive(Long id, ChangeActiveRequest request) {
        // TODO: deve estar logado

        Reader reader = findEntityById(id);

        if (!request.active() && loanRepository.existsByReaderIdAndStatus(id, LoanStatus.ACTIVE)) {
            throw new BusinessRuleException("Não é possível excluir um leitor com empréstimos ativos.");
        }

        reader.setActive(request.active());
        return readerMapper.toResponse(reader);
    }
}
