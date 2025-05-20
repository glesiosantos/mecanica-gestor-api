package br.com.autorevise.mecanicagestor.api.entities;

import br.com.autorevise.mecanicagestor.api.enuns.TipoPessoa;
import br.com.autorevise.mecanicagestor.api.utils.conversor.CPFConversor;
import br.com.autorevise.mecanicagestor.api.utils.conversor.UpperCaseConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente extends EntidadeAbstrata {

    @Convert(converter = CPFConversor.class)
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Convert(converter = UpperCaseConverter.class)
    @Column(name = "razao")
    private String razaoSocial;

    @Convert(converter = UpperCaseConverter.class)
    @Column(name = "nm_completo")
    private String nomeCompleto;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "cliente_contatos")
    private List<String> contatos;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "cliente_veiculo",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "veiculo_id"))
    private Set<Veiculo> veiculos = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoPessoa tipoPessoa;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "cliente_estabelecimento",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "estabelecimento_id"))
    private Set<Estabelecimento> estabelecimentos = new HashSet<>();
}
