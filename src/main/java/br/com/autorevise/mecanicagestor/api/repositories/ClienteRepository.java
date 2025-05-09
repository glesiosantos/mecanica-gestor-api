package br.com.autorevise.mecanicagestor.api.repositories;

import br.com.msoficinas.api.entidades.Cliente;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);
}
