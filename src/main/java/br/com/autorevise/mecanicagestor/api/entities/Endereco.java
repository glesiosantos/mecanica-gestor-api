package br.com.autorevise.mecanicagestor.api.entities;

import br.com.autorevise.mecanicagestor.api.enuns.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    @Column(nullable = false, length = 10)
    private String cep;

    @Column(nullable = false, length = 200)
    private String logradouro;

    @Column(nullable = false, length = 100)
    private String bairro;

    @Column(nullable = false, length = 60)
    private String cidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "uf", nullable = false, length = 2, columnDefinition = "CHAR(2) default 'PI'")
    private Estado estado;

    @Column(columnDefinition = "TEXT")
    private String complemento;

    @Column(length = 60)
    private String latitude;

    @Column(length = 60)
    private String longitude;
}
