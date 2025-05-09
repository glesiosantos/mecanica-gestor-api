package br.com.autorevise.mecanicagestor.api.web.response;

public record VeiculoFormResponse(
        String idVeiculo,
        String placa,
        String tipoVeiculo,
        String marca,
        String modelo,
        int ano
) {}
