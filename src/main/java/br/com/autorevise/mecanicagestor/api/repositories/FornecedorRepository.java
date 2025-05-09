package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, String> {

    @Query("SELECT f FROM Fornecedor f JOIN FETCH f.estabelecimento e WHERE e.id = :idEstabelecimento")
    List<Fornecedor> findFornecedoresByEstabelecimento(String idEstabelecimento);

    Optional<Fornecedor> findByIdAndEstabelecimentoId(String id, String estabelecimentoId);
}
