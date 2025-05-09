package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotNull;

public record AtualizarStatusOficinaRequest(
        @NotNull(message = "CODIGO do pedido é obrigatório") String idOrdem,
        @NotNull(message = "NOVO Status é obrigatório") String statusOficina,
        String descricaoPendencia
) {
}
