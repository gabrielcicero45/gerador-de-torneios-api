package br.com.xadrez.torneios.controller.form;

import br.com.xadrez.torneios.model.*;
import br.com.xadrez.torneios.repository.GameRepository;
import br.com.xadrez.torneios.repository.PerformanceRepository;
import br.com.xadrez.torneios.repository.PlayerRepository;
import br.com.xadrez.torneios.repository.RoundRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class TournamentForm {
    private String name;
    private List<String> players;
    private List<Round> rounds = new ArrayList<> ();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
    // Metodo auxiliar na construcao do torneio:
    // Faz o emparceiramento inicial
    private int[] initialPositions(int length) {
        int[] pos = new int[length];
        pos[0] = 0;
        for (int i = 1; i<= length-3; i=i+2) {
            pos[i] = i;
            pos[i+1] = length - 1 - i;
        }
        pos[length - 1] = length - 1;
        return pos;
    }

    // Metodo auxiliar na construcao do torneio
    // Muda o emparceiramento a cada rodada
    private int nextPosition(int pos, int length) {
        if (pos > 1) {
            return pos - 1;
        } else {
            return length - 1;
        }
    }

    // Cria um torneio completo de todos contra todos
    public void newSchuringTournament(List<Player> p,GameRepository gameRepository ,RoundRepository roundRepository) {
        int numRounds = p.size() - 1;
        Collections.shuffle(p);
        int[] pos = initialPositions(p.size());
        for (int i = 0; i <= numRounds-1; i++) {
            Round round = new Round();
            round.setTournamentName(name);
            List<Game> games = new ArrayList<>();
            if (i % 2 == 0) {
                Game game = new Game(p.get(pos[0]),p.get(pos[pos.length -1]),name);
                gameRepository.save(game);
                games.add(game);

            } else {
                Game game = new Game(p.get(pos[0]),p.get(pos[pos.length -1]),name);
                gameRepository.save(game);
                games.add(game);
            }
            for (int j = 1; j<= p.size()-2; j=j+2) {
                    Game game = new Game(p.get(pos[j]),p.get(pos[j+1]),name);
                    gameRepository.save(game);
                    games.add(game);
            }
            round.setGames(games);
            roundRepository.save(round);
            rounds.add(round);
            for (int j = 1; j<= p.size()-1; j++) {
                pos[j] = nextPosition(pos[j], p.size());
            }
        }
    }

    public Tournament convert(PlayerRepository playerRepository, GameRepository gameRepository, RoundRepository roundRepository, PerformanceRepository performanceRepository) throws ResponseStatusException {

        List<Player> playersList = new ArrayList<>();

        for (String player:players) {
            Optional<Player> pl = playerRepository.findByName(player);
            if (pl.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tentou cadastrar torneio com jogador inexistente");
            }

            List<Performance> perfomances = pl.get().getPerfomances();
            Performance perfomance = new Performance(name);
            performanceRepository.save(perfomance);
            perfomances.add(perfomance);
            pl.get().setPerfomances(perfomances);
            playersList.add(pl.get());
        }
        newSchuringTournament(playersList,gameRepository, roundRepository);
        List<Round> roundsList= roundRepository.findByTournamentName(name);
        return new Tournament(name,playersList,roundsList);
    }
}
