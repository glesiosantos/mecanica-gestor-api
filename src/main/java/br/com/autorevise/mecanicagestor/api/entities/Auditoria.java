package br.com.autorevise.mecanicagestor.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditoria extends EntidadeAbstrata{

    @CreatedDate
    @Column(name = "dt_criado_em", nullable = false, updatable = false, columnDefinition = "DATE default 'now()'")
    private LocalDate dataCriacao;

    @LastModifiedDate
    @Column(name = "dt_atualizado_em", columnDefinition = "DATE")
    private LocalDate dataAtualizacao;
}
