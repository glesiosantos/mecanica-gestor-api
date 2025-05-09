package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOficina {

    AG("Aguardando"),
    EA("Em Andamento"),
    PE("Pendente"),
    CO("Concluído");

    private final String descricao;
}
