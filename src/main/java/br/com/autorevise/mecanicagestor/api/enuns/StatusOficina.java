package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusOficina {

    AG("Aguardando"),
    EA("Em Andamento"),
    PE("Pendente"),
    CO("Concluído"),
    NA("Não atribuído");

    private final String descricao;
}
