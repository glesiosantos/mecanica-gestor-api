package br.com.autorevise.mecanicagestor.api.entities;

import br.com.msoficinas.api.enuns.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marcas")
public class Marca extends EntidadeAbstrata {
    private String nome;
    private String logo;
}
