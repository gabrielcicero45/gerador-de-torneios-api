package br.com.xadrez.torneios.repository;

import br.com.xadrez.torneios.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoundRepository extends JpaRepository<Round,Long> {
    List<Round> findByTournamentName(String tournamentName);
}
