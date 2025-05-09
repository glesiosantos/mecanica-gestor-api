package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.msoficinas.api.entidades.Cliente;
import br.com.msoficinas.api.entidades.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, String> {
    Optional<Estabelecimento> findByCpfOuCnpj(String cpfOuCnpj);

    @Query("SELECT e FROM Estabelecimento e JOIN FETCH e.usuarios WHERE e.id = :id")
    Optional<Estabelecimento> findByIdComUsuarios(@Param("id") String id);

    @Query("SELECT c FROM Estabelecimento e JOIN e.clientes c WHERE e.id = :estabelecimentoId AND c.id = :clienteId")
    Optional<Cliente> findByIdAndClienteId(@Param("clienteId") String clienteId, @Param("estabelecimentoId") String estabelecimentoId);
}
