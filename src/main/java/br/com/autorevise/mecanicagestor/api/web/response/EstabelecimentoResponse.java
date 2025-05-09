package br.com.autorevise.mecanicagestor.api.web.response;

public record EstabelecimentoResponse(
        String idEstabelecimento,
        String logo,
        String nome,
        String cpfCnpj,
        String plano
){}
