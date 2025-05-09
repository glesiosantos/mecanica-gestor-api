package br.com.autorevise.mecanicagestor.api.web.request;

import br.com.msoficinas.api.web.validacao.CPFouCNPJ;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClienteAddRequest (
        @NotBlank(message = "CPF ou CNPJ é obrigatório") @CPFouCNPJ(message = "CPF ou CNPJ invalido") String cpfOuCnpj,
        @NotBlank(message = "Tipo de Pessoa é obrigatório") String tipoCliente,
        @NotBlank(message = "Nome do Cliente é obrigatório") String nome,
        @NotBlank(message = "Razão Social é obrigatório") String razao,
        @NotEmpty(message = "Deverá conter pelo menos 1 contato") List<String> contatos,
        @NotNull(message = "Estabelecimento é obrigatório") String idEstabelecimento
) {}
