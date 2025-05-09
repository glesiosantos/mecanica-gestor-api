package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeiculoRequest(
   String idVeiculo,
   @NotBlank(message = "Placa é obrigatório") String placa,
   @NotNull(message = "Ano do carro é obrigatório") int ano,
   @NotNull(message = "Modelo é obrigatório")  String marca,
   @NotNull(message = "Modelo é obrigatório")  String modelo
) {}
