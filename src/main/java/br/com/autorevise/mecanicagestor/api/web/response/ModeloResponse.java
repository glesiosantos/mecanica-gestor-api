package br.com.autorevise.mecanicagestor.api.web.response;

public record ModeloResponse(
        String idModelo,
        String modelo,
        String idMarca,
        String marca,
        String tipoVeiculo
) {}
