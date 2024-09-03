package br.com.OS.repository;

import br.com.OS.model.Ambiente;
import br.com.OS.model.EnderecoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmbienteRepository extends JpaRepository<Ambiente, Long> {
}
