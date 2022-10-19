package br.com.xadrez.torneios.controller.dto;

import br.com.xadrez.torneios.model.Round;
import br.com.xadrez.torneios.model.Tournament;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TournamentDto {
    private String name;
    private List<PlayerDto> players;
    private List<Round> rounds;

    public TournamentDto(Tournament tournament){
        this.name = tournament.getName();
        this.players = PlayerDto.converter(tournament.getPlayers());
        this.rounds = tournament.getRounds();
    }

    public TournamentDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDto> players) {
        this.players = players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public static List<TournamentDto> converterList(List<Tournament> tournament){
        List<TournamentDto> list = new ArrayList<>();
        for(Tournament tr: tournament){
            TournamentDto tournamentDto = new TournamentDto(tr);
            list.add(tournamentDto);
        }
        return list;
    }
    public static TournamentDto converter(Optional<Tournament> tournament){
        if (tournament.isPresent()){
            TournamentDto tournamentDto = new TournamentDto(tournament.get());
            return tournamentDto;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
