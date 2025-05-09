package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u " +
            "JOIN u.estabelecimentos e " +
            "WHERE e.id = :idEstabelecimento")
    List<Usuario> findUsuariosByEstabelecimentoId(@Param("idEstabelecimento") String idEstabelecimento);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.estabelecimentos WHERE u.cpf = :cpf")
    Optional<Usuario> findByCpfComEstabelecimentos(@Param("cpf") String cpf);

    Optional<Usuario> findUsuarioByCpf(String cpf);

    Optional<Usuario> findByIdAndEstabelecimentosId(String idUsuario, String idEstabelecimento);
}
