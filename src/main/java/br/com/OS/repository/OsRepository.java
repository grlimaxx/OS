package br.com.OS.repository;

import br.com.OS.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OsRepository extends JpaRepository<OS, Long> {
}

