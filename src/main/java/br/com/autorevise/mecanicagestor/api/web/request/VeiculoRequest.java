package br.com.autorevise.mecanicagestor.api.web.request;

public record VeiculoRequest(
   String idVeiculo,
   String placa,
   int ano,
   String marca,
   String modelo
) {}
