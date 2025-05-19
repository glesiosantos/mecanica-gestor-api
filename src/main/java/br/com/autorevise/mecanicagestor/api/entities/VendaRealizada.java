package br.com.autorevise.mecanicagestor.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venda_realizadas")
public class VendaRealizada extends EntidadeAbstrata {

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @OneToOne
    @JoinColumn(name = "ordem_id")
    private Ordem ordem;

    @CreatedDate
    @Column(name = "dt_realizacao")
    private LocalDate dataRealizacao;

    @Column(name = "vl_tt_vend_produtos")
    private Double valorTotalProdutos;

    @Column(name = "vl_tt_vend_servicos")
    private Double valorTotalServicos;

    @Column(name = "vl_ordem_bruto")
    private Double valorFinalBruto;

    @Column(name = "vl_ordem_desconto")
    private Double valorFinalComDesconto;

    @Column(name = "vl_entrada")
    private Double valorEntrada;

    @Column(name = "desconto")
    private int percentualDesconto;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @Column(name = "nr_parcelas")
    private int numeroParcela;

    @Column(name = "atendido_na_oficina")
    private boolean foiParaOficina;

    @Column(name = "responsavel")
    private String responsavelVenda;
}
