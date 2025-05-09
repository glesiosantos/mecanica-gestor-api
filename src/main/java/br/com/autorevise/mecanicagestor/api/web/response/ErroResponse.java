package br.com.autorevise.mecanicagestor.api.web.response;

public record ErroResponse(
        String mensagem,
        int status,
        long horario,
        String path
) {}
