package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
    Optional<Veiculo> findByPlaca(String placa);

    @Query("SELECT v FROM Veiculo v JOIN v.clientes c WHERE c.id = :clienteId AND v.placa = :placa")
    Optional<Veiculo> findByClienteIdAndPlaca(@Param("clienteId") String clienteId, @Param("placa") String placa);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END " +
            "FROM Veiculo v JOIN v.clientes c WHERE v.placa = :placa AND c.id = :clienteId")
    boolean existsByPlacaAndClienteId(@Param("placa") String placa, @Param("clienteId") String clienteId);
}
