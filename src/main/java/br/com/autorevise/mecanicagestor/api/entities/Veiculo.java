package br.com.autorevise.mecanicagestor.api.entities;

import br.com.autorevise.mecanicagestor.api.utils.conversor.UpperCaseConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "veiculos")
public class Veiculo extends EntidadeAbstrata {

    @Convert(converter = UpperCaseConverter.class)
    private String placa;

    private String modelo;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    private int ano;

    @JsonIgnore
    @ManyToMany(mappedBy = "veiculos")
    private Set<Cliente> clientes = new HashSet<>();
}
