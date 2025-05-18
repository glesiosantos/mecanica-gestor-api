package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.web.response.DashboardResponse;

public interface DashboardService {
    
    DashboardResponse obterDadosEstatitiscoDoEstabelecimento(String idEstabelecimento);
}
