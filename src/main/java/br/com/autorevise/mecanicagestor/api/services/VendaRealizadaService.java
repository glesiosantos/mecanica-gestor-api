package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Ordem;

public interface VendaRealizadaService {

    void registrarVendaConcluida(Ordem ordem);
}
