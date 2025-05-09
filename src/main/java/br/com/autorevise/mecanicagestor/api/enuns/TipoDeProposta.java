package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDeProposta {

    O("Orçamento"),
    P("Pedido");

    private final String descricao;
}
