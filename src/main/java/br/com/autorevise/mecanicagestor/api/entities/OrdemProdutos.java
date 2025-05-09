package br.com.autorevise.mecanicagestor.api.entities;

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
@Table(name = "ordem_produtos")
public class OrdemProdutos extends EntidadeAbstrata {

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "ordem_id")
    private Ordem ordem;
}
