package br.com.autorevise.mecanicagestor.api.web.response;

import java.util.List;

public record ClienteResponse (
        String idCliente,
        String razao,
        String nome,
        String cpfOuCnpj,
        String tipo,
        List<String> contatos,
        List<VeiculoResponse> veiculos
) {}
