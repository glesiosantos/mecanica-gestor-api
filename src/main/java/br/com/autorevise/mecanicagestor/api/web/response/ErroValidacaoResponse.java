package br.com.autorevise.mecanicagestor.api.web.response;

public record ErroValidacaoResponse(
        String campo,
        String mensagem
) {
}
