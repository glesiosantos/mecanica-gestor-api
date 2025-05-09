package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotNull;

public record FornecedorFilterRequest(
        @NotNull(message = "ID do fornecedor é obrigatório") String idFornecedor,
        @NotNull(message = "ID do estabelecimento é obrigatório") String idEstabelecimento
) {}
