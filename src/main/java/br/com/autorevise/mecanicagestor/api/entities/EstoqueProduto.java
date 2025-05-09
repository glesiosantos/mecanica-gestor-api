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
@Table(name = "estoques")
public class EstoqueProduto extends Auditoria {

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "qtd_adquirida")
    private int quantidade;

    @Column(name = "qtd_anterior")
    private int quantidadeAnterior;

    @Column(name = "preco_custo")
    private Double precoDeCusto;
}
