package br.com.autorevise.mecanicagestor.api.web.response;

public record PlanoResponse(
   String nome,
   String descricao,
   int totalUsuario,
   double preco
) {}
