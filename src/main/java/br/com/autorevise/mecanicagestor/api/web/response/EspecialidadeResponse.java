package br.com.autorevise.mecanicagestor.api.web.response;

public record EspecialidadeResponse(
        String idEspecialidade,
        String nomeEspecialidade,
        String categoria,
        String tipo
) {}
