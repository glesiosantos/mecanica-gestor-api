package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePlanoRequest (
        @NotNull(message = "ID do estabelecimento é obrigatório") String idEstabelecimento,
        @NotBlank(message = "Plano deverá ser informado") String novoPlano
) {}
