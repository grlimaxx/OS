package br.com.OS.repository;

import br.com.OS.model.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OsRepository extends JpaRepository<OS, Long> {
    // Executa Stored Procedure ImprimirServicosporMes() com query nativa
    @Query(value = "CALL ImprimirQuantidadeServicosporMes()", nativeQuery = true)
    List<Object[]> imprimirServicosPorMes();
}

