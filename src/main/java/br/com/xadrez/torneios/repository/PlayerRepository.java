package br.com.xadrez.torneios.repository;

import br.com.xadrez.torneios.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {

    Optional<Player> findByName(String name);


}
