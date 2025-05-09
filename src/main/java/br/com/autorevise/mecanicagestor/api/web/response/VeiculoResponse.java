package br.com.autorevise.mecanicagestor.api.web.response;

public record VeiculoResponse (
        String idVeiculo,
        String placa,
        String marca,
        String modelo,
        int ano
) {}
