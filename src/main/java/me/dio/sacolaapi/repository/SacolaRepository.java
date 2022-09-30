package me.dio.sacolaapi.repository;

import me.dio.sacolaapi.model.Sacola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Classe para eu me reconectar à minha tabela de Sacola. no BD.

@Repository
public interface SacolaRepository extends JpaRepository<Sacola, Long> {
}
