package br.com.xadrez.torneios.repository;

import br.com.xadrez.torneios.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TournamentRepository  extends JpaRepository<Tournament,Long>{
    Optional<Tournament> findByName(String name);
}
