package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Ordem;
import br.com.autorevise.mecanicagestor.api.entities.VendaRealizada;
import br.com.autorevise.mecanicagestor.api.repositories.OrdemRepository;
import br.com.autorevise.mecanicagestor.api.services.DashboardService;
import br.com.autorevise.mecanicagestor.api.services.OrdemService;
import br.com.autorevise.mecanicagestor.api.services.VendaRealizadaService;
import br.com.autorevise.mecanicagestor.api.web.response.CardDashboardResponse;
import br.com.autorevise.mecanicagestor.api.web.response.DashboardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private VendaRealizadaService vendaRealizadaService;

    @Autowired
    private OrdemRepository ordemRepository;

    @Override
    public DashboardResponse obterDadosEstatitiscoDoEstabelecimento(String idEstabelecimento) {

        ordemRepository.findAll().stream().forEach(o -> vendaRealizadaService.registrarVendaConcluida(o));

        List<CardDashboardResponse> cards = new ArrayList<>();
        cards.add(new CardDashboardResponse("Vendas do Mês","0","monetization_on"));
        cards.add(new CardDashboardResponse("Total de Cliente atendidos","0","people"));
        cards.add(new CardDashboardResponse("Pedidos Concluído do Mês","0","bar_chat"));
        cards.add(new CardDashboardResponse("Pedidos Cancelado do Mẽs","0","hourglass_empty"));

        return new DashboardResponse(cards, List.of(1500,2500,1890));
    }
}
