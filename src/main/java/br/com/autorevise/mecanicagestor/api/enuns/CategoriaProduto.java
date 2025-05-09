package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaProduto {
    AS("Acessórios"),
    MO("Motor"),
    SU("Suspensão"),
    FR("Freios"),
    EL("Elétrico"),
    RE("Refrigeração"),
    OU("Outros");

    private final String titulo;
}
