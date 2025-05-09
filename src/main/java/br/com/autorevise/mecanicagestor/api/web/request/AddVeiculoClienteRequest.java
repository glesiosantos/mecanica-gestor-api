package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotNull;

public record AddVeiculoClienteRequest(
        @NotNull(message = "Cliente id é obrigatório") String idCliente,
        VeiculoRequest veiculo
) {}
