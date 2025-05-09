package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoVeiculo {

    C("Carro"),M("Moto");

    private final String tipo;
}
