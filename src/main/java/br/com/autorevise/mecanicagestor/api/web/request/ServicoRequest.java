package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotNull;

public record ServicoRequest(
   String idServico,
   @NotNull(message = "Descricao é obrigatório")  String descricao,
   @NotNull(message = "Valor do serviço é obrigatório") Double valor,
   @NotNull(message = "Estabelecimento é obrigatório") String idEstabelecimento
) {}
