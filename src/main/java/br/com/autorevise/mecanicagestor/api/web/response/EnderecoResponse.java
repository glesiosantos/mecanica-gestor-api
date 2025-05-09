package br.com.autorevise.mecanicagestor.api.web.response;

public record EnderecoResponse(
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String latitude,
        String longitude
) {
}
