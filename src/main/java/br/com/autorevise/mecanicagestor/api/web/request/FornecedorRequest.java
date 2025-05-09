package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record FornecedorRequest(
        String idFornecedor,
        @NotBlank(message = "Nome do fornecedor é obrigatório") String nomeFornecedor,
        @NotEmpty(message = "Contato não pode ser vazio") List<String> contatos,
        @NotNull(message = "ID do estabelecimento é obrigatório") String idEstabelecimento
) {}
