package br.com.OS.repository;

import br.com.OS.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository <Telefone,Long> {
}
