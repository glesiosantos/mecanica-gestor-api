package br.com.autorevise.mecanicagestor.api.web.response;

import java.util.List;

public record FormClienteResponse(
        String idCliente,
        String razao,
        String nome,
        String cpfOuCnpj,
        String tipo,
        List<String> contatos
) {}
