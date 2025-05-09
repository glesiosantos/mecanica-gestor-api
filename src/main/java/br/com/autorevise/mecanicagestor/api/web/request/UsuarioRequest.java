package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioRequest(
        String idUsuario,
        @NotNull(message = "Estabelecimento é obrigatório") String idEstabelecimento,
        @NotBlank(message = "CPF é obrigatório") @CPF(message = "CPF inválido") String cpf,
        @NotBlank(message = "NOME COMPLETO é obrigatório") String nomeCompleto,
        @NotBlank(message = "PERFIl é obrigatório") String perfil
) {
}
