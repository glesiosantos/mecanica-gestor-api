package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Plano {

    BASIC("Básico", 29.9, 2),
    INTER("Intermediário", 59.9, 5),
    PROFI("Profissional", 99.9, 10),
    TESTE("Teste", 0.0, 1);

    private final String descricao;
    private final double valor;
    private final int totalUsuario;
}
