package br.com.autorevise.mecanicagestor.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaEspecialidade {

    MG("Mecânica Geral"),
    SF("Sistema de Freios"),
    SD("Suspensão e Direção"),
    IC("Injeção e Combustão"),
    SE("Sistema Elétrico"),
    AC("Arrefecimento e Climatização"),
    TR("Transmissão"),
    EL("Estética e Lataria"),
    ES("Escapamento"),
    PN("Pneus"),
    PE("Performance"),
    CA("Conforto e Acessórios");

    private final String titulo;
}
