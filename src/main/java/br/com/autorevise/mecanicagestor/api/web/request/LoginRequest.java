package br.com.autorevise.mecanicagestor.api.web.request;

public record LoginRequest(
   String cpf,
   String senha
) {}
