package br.com.xadrez.torneios.repository;


import br.com.xadrez.torneios.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceRepository extends JpaRepository<Performance,Long> {
}
