package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotNull;

public record AtualizarOrdemStatusRequest(
        @NotNull(message = "CODIGO do pedido obrigatório") String idOrdem,
        @NotNull(message = "NOVO Status do pedido obrigatório") String novoStatus
) {
}
