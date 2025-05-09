package br.com.autorevise.mecanicagestor.api.entities;

import br.com.autorevise.mecanicagestor.api.enuns.CategoriaEspecialidade;
import br.com.autorevise.mecanicagestor.api.enuns.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especialidades")
public class Especialidade extends EntidadeAbstrata {

    private String nome;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaEspecialidade categoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private TipoVeiculo tipo;
}
