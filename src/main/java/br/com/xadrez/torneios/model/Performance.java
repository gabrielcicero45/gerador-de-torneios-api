package br.com.xadrez.torneios.model;

import javax.persistence.*;

@Entity
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private int points;
    @Column
    private int numWins;
    @Column
    private int numDraws;
    @Column
    private int numLosses;
    @Column
    private String tournamentName;


    public Performance() {
    }

    // Construtor
    public Performance(String tournamentName) {
        this.points = 0;
        this.numWins = 0;
        this.numDraws = 0;
        this.numLosses = 0;
        this.tournamentName = tournamentName;
    }

    public Long getId() {
        return id;
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
}
