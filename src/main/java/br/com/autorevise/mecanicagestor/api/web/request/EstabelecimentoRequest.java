package br.com.autorevise.mecanicagestor.api.web.request;

import java.time.LocalDate;

public record EstabelecimentoRequest(
        String idCliente,
        String documento,
        String razao,
        String fantasia,
        String cpfProprietario,
        String proprietario,
        LocalDate dataVencimentoTeste,
        int diaVencimento,
        String cep,
        String plano,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String latitude,
        String longitude
) {}
