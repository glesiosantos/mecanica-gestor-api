package br.com.autorevise.mecanicagestor.api.entities;

import br.com.msoficinas.api.enuns.FormaDePagamento;
import br.com.msoficinas.api.enuns.OrdemStatus;
import br.com.msoficinas.api.enuns.StatusOficina;
import br.com.msoficinas.api.enuns.TipoDeProposta;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordens")
public class Ordem extends Auditoria {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    private String observacoes;

    private Double desconto;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento")
    private FormaDePagamento formaDePagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tp_proposta")
    private TipoDeProposta tipoProposta;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrdemStatus ordemStatus;

    @Column(name = "valor_entrada")
    private Double valorEntrada;

    @Column(name = "parcelas")
    private int totalParcelas;

    @Column(name = "dias_proposta")
    private int totalDiasProposta;

    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "ordem_pendencias", joinColumns = @JoinColumn(name = "ordem_id"))
    private List<String> pendencias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status_oficina")
    private StatusOficina statusOficina;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ordem", cascade = CascadeType.PERSIST)
    private Set<OrdemProdutos> produtos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "ordem_servicos",
            joinColumns = @JoinColumn(name = "ordem_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private  Set<ServicoEstabelecimento> servicos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;
}
