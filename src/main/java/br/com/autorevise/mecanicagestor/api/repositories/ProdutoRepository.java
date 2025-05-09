package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    @Query("SELECT p FROM Produto p JOIN FETCH p.estabelecimento e WHERE e.id = :idEstabelecimento")
    List<Produto> findProdutosByEstabelecimento(String idEstabelecimento);

    Optional<Produto> findByCodigoProdutoAndEstabelecimentoId(String codigo, String estabelecimentoId);

    Optional<Produto> findByIdAndEstabelecimentoId(String idProduto, String estabelecimentoId);
}
