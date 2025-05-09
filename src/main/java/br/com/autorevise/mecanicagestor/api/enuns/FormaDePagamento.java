package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FormaDePagamento {

    DI("Dinheiro"),
    PX("PIX"),
    CC("Cartão de Crédito"),
    CD("Cartão de Debito"),
    TB("Transferência Bancária");

    private final String descricao;
}
