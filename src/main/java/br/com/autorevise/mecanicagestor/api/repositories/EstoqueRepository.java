package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.EstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstoqueRepository extends JpaRepository<EstoqueProduto, String> {

    @Query("SELECT e FROM EstoqueProduto e WHERE e.produto.id = :idProduto")
    List<EstoqueProduto> findByProdutoId(@Param("idProduto") String idProduto);
}
