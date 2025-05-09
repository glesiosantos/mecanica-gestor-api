package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, String> {}
