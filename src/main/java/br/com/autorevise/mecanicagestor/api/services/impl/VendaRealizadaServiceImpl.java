package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Ordem;
import br.com.autorevise.mecanicagestor.api.entities.VendaRealizada;
import br.com.autorevise.mecanicagestor.api.entities.VendaRealizadaProduto;
import br.com.autorevise.mecanicagestor.api.entities.VendaRealizadaServicos;
import br.com.autorevise.mecanicagestor.api.enuns.StatusOficina;
import br.com.autorevise.mecanicagestor.api.repositories.VendaRealizadaRepository;
import br.com.autorevise.mecanicagestor.api.services.VendaRealizadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaRealizadaServiceImpl implements VendaRealizadaService {

    @Autowired
    private VendaRealizadaRepository vendaRealizadaRepository;

    @Override
    public void registrarVendaConcluida(Ordem ordem) {

        List<VendaRealizadaProduto> produtos = new ArrayList<>();
        List<VendaRealizadaServicos> servicos = new ArrayList<>();

        VendaRealizada vendaRealizada = VendaRealizada.builder()
                .estabelecimento(ordem.getEstabelecimento())
                .cliente(ordem.getCliente())
                .veiculo(ordem.getVeiculo())
                .ordem(ordem)
                .dataRealizacao(LocalDate.now())
                .statusPedido(ordem.getOrdemStatus().getDescricao())
                .formaPagamento(ordem.getFormaDePagamento().getDescricao())
                .numeroParcela(ordem.getTotalParcelas())
                .valorEntrada(ordem.getValorEntrada())
                .responsavelVenda(ordem.getResponsavel().getNomeCompleto())
                .foiParaOficina(!StatusOficina.NA.equals(ordem.getStatusOficina()))
                .build();

        double valorTotalProdutos = 0.0;
        double valorTotalServicos = 0.0;

        if(!ordem.getProdutos().isEmpty()) {
            ordem.getProdutos().stream().forEach(p -> produtos.add(
                    VendaRealizadaProduto.builder()
                            .produto(p.getProduto())
                            .quantidadeVenda(p.getQuantidade())
                            .valorVenda(p.getProduto().getPrecoDeVenda())
                            .vendaRealizada(vendaRealizada)
                            .subtotal(p.getQuantidade() * p.getProduto().getPrecoDeVenda())
                            .build()
            ));
            valorTotalProdutos =  produtos.stream().mapToDouble(VendaRealizadaProduto::getSubtotal).sum();
            vendaRealizada.setValorTotalProdutos(valorTotalProdutos);
        } else {
            vendaRealizada.setValorTotalProdutos(0.0);
        }

        if(!ordem.getServicos().isEmpty()) {
            ordem.getServicos().stream().forEach(s -> servicos.add(VendaRealizadaServicos.builder()
                    .servico(s)
                    .valorVenda(s.getValor())
                    .descricao(s.getDescricao())
                    .vendaRealizada(vendaRealizada)
                    .build()));

            valorTotalServicos = servicos.stream().mapToDouble(VendaRealizadaServicos::getValorVenda).sum();
            vendaRealizada.setValorTotalServicos(valorTotalServicos);
        } else {
            vendaRealizada.setValorTotalServicos(0.0);
        }

        vendaRealizada.setValorFinalBruto(valorTotalProdutos + valorTotalServicos);
        vendaRealizada.setValorDesconto(Optional.ofNullable(vendaRealizada.getValorFinalBruto() * (ordem.getDesconto()/100)).orElse(0.0));
        vendaRealizada.setValorFinalComDesconto(vendaRealizada.getValorFinalBruto() - vendaRealizada.getValorDesconto());
        vendaRealizadaRepository.save(vendaRealizada);
    }
}
