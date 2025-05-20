package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.autorevise.mecanicagestor.api.entities.VendaRealizada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRealizadaRepository extends JpaRepository<VendaRealizada, String> {
}
