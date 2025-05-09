package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.msoficinas.api.entidades.Estabelecimento;
import br.com.msoficinas.api.entidades.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface OrdemRepository extends JpaRepository<Ordem, String> {

    @Query("SELECT o FROM Ordem o JOIN FETCH o.estabelecimento e WHERE e.id = :idEstabelecimento")
    List<Ordem> findOrdemByEstabelecimento(String idEstabelecimento);

    Optional<Ordem> findByIdAndEstabelecimentoId(String idOrdem, String idEstabelecimento);

    List<Ordem> findByDataCriacaoAndEstabelecimento(Instant dataCriacao, Estabelecimento estabelecimento);

    List<Ordem> findByDataCriacaoBetweenAndEstabelecimento(Instant inicio, Instant fim, Estabelecimento estabelecimento);
}
