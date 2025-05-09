package br.com.autorevise.mecanicagestor.api.entities;

import br.com.autorevise.mecanicagestor.api.enuns.Plano;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estabelecimentos")
public class Estabelecimento extends Auditoria {

    @Column(name = "cpf_cnpj", length = 15, nullable = false, unique = true)
    private String cpfOuCnpj;

    @Column(name = "cod_integracao")
    private String clienteCodigoIntegracao;

    @Column(name = "razao", length = 150, nullable = false, unique = true)
    private String razaoSocial;

    @Column(name = "nm_fantasia", length = 150, nullable = false)
    private String nomeFantasia;

    private String logo;

    private boolean ativo;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private Plano plano;

    @Column(name = "vencimento", nullable = false)
    private int diaVencimento;

    @Column(name = "dt_fim_teste")
    private LocalDate dataFinalDoTeste;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "estabelecimento_contatos",
            joinColumns = @JoinColumn(name = "estabelecimento_id"))
    private Set<String> contatos;

    @JsonIgnore
    @ManyToMany(mappedBy = "estabelecimentos", cascade = CascadeType.ALL)
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToMany(mappedBy = "estabelecimentos", fetch = FetchType.EAGER)
    private Set<Cliente> clientes;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private Set<ServicoEstabelecimento> servicos;
}
