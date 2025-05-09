package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPessoa {
    PF("Pessoa Física"), PJ("Pessoa Jurídica");

    private final String nome;
}
