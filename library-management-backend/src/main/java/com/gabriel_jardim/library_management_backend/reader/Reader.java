package com.gabriel_jardim.library_management_backend.reader;

import com.gabriel_jardim.library_management_backend.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reader extends BaseEntity {
    
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(length = 100)
    private String email;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
}
