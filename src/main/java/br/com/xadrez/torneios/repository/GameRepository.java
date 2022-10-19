package br.com.xadrez.torneios.repository;

import br.com.xadrez.torneios.model.Game;
import br.com.xadrez.torneios.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game,Long> {
}
