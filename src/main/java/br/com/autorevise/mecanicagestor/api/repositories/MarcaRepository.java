package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, String> {
}
