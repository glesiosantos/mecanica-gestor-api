package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.msoficinas.api.entidades.ServicoEstabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<ServicoEstabelecimento, String> {

    List<ServicoEstabelecimento> findByEstabelecimentoId(String idEstabelecimento);

    Optional<ServicoEstabelecimento> findByIdAndEstabelecimentoId(String idServico, String estabelecimentoId);
}
