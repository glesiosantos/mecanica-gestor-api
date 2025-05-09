package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotBlank;

public record UsuarioUpdateSenhaRequest(
        @NotBlank(message = "CPF do usuário é obrigatório") String cpf,
        @NotBlank(message = "NOVA SENHA é obrigatória") String nova
) {
}
