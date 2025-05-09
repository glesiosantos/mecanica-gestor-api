package br.com.autorevise.mecanicagestor.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fornecedores")
public class Fornecedor extends EntidadeAbstrata {

    @Column(name = "nome")
    private String nomeFornecedor;

    @ElementCollection
    @JoinTable(name = "fornecedor_contatos", joinColumns = @JoinColumn(name = "fornecedor_id", nullable = false))
    private List<String> contatos;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "estabelecimento_id", nullable = false, updatable = false)
    private Estabelecimento estabelecimento;
}
