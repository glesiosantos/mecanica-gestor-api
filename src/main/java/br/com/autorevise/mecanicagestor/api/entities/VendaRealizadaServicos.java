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
@Table(name = "venda_realizada_servicos")
public class VendaRealizadaServicos extends EntidadeAbstrata {

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private ServicoEstabelecimento servico;

    @Column(name = "vl_venda")
    private Double valorVenda;

    private String descricao;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "venda_realizada_id")
    private VendaRealizada vendaRealizada;
}
