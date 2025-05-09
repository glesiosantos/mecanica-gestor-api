package br.com.autorevise.mecanicagestor.api.entities;

import br.com.msoficinas.api.conversor.CPFConversor;
import br.com.msoficinas.api.enuns.Perfil;
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
@Table(name = "usuarios")
public class Usuario extends Auditoria {

    @Column(name = "avatar", length = 150, columnDefinition = "VARCHAR(150) default 'default.png'")
    private String avatar;

    @Convert(converter = CPFConversor.class)
    private String cpf;

    @Column(name = "nm_completo")
    private String nomeCompleto;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    private String senha;

    private boolean ativo;

    @Column(name = "principal")
    private boolean usuarioPrincipal;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_estabelecimento",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "estabelecimento_id"))
    private Set<Estabelecimento> estabelecimentos = new HashSet<>();
}
