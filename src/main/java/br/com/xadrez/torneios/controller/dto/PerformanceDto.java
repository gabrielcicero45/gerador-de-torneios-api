package br.com.xadrez.torneios.controller.dto;

import br.com.xadrez.torneios.model.Performance;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;

public class PerformanceDto {

    private int points;
    private int numWins;
    private int numDraws;
    private int numLosses;
    private String tournamentName;

    public PerformanceDto(Performance performance) {
        this.points = performance.getPoints();
        this.numWins = performance.getNumWins();
        this.numDraws = performance.getNumDraws();
        this.numLosses = performance.getNumLosses();
        this.tournamentName = performance.getTournamentName();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public int getNumDraws() {
        return numDraws;
    }

    public void setNumDraws(int numDraws) {
        this.numDraws = numDraws;
    }

    public int getNumLosses() {
        return numLosses;
    }

    public void setNumLosses(int numLosses) {
        this.numLosses = numLosses;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public static List<PerformanceDto> converter(List<Performance> perfomances) {
       return perfomances.stream().map(PerformanceDto::new).collect(Collectors.toList());
    }
}
