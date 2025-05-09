package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.msoficinas.api.entidades.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, String> {
}
