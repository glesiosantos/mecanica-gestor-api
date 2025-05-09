package br.com.autorevise.mecanicagestor.api.entities;

import br.com.msoficinas.api.enuns.CategoriaProduto;
import br.com.msoficinas.api.utils.conversor.UpperCaseConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos")
public class Produto extends EntidadeAbstrata {

    @Column(name = "cod_produto")
    private String codigoProduto;

    @Convert(converter = UpperCaseConverter.class)
    @Column(name = "referencia")
    private String referenciaProduto;

    @Convert(converter = UpperCaseConverter.class)
    @Column(name = "descricao")
    private String descricaoProduto;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private CategoriaProduto categoriaProduto;

    @Column(name = "preco_custo")
    private Double precoCusto;

    @Column(name = "percentual")
    private Double percentualLucro;

    @Column(name = "qtd_minima_estoque")
    private int quantidadeMinimaEstoque;

    @Column(name = "qtd_atual_estoque")
    private int quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @OneToMany(mappedBy = "produto")
    private List<EstoqueProduto> registros;

    public Double getPrecoDeVenda() {
        return this.precoCusto * (1 + this.percentualLucro/100 );
    }
}
