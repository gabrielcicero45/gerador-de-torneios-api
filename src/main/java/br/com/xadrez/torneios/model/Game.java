package br.com.xadrez.torneios.model;

import br.com.xadrez.torneios.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Player white;
    @ManyToOne
    private Player black;
    @Column
    private String tournamentName;
    @Column
    private int whiteResult;
    @Column
    private int blackResult;
    @Column
    private boolean whiteResultRegistered;
    @Column
    private boolean blackResultRegistered;


    public Game(Player white, Player black, String tournamentName) {
        this.white = white;
        this.black = black;
        this.tournamentName = tournamentName;
        this.whiteResult = 0;
        this.blackResult = 0;
        this.whiteResultRegistered = false;
        this.blackResultRegistered = false;
    }

    public Game() {}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getWhite() {
        return white;
    }

    public Player getBlack() {
        return black;
    }
    public void setWhite(Player white) {
        this.white = white;
    }

    public void setBlack(Player black) {
        this.black = black;
    }

    public boolean isWhiteResultRegistered() {
        return whiteResultRegistered;
    }

    public void setWhiteResultRegistered(boolean whiteResultRegistered) {
        this.whiteResultRegistered = whiteResultRegistered;
    }

    public boolean isBlackResultRegistered() {
        return blackResultRegistered;
    }

    public void setBlackResultRegistered(boolean blackResultRegistered) {
        this.blackResultRegistered = blackResultRegistered;
    }

    public int getWhiteResult() {
        return whiteResult;
    }

    public void setWhiteResult(int whiteResult) {
        this.whiteResult = whiteResult;
    }

    public int getBlackResult() {
        return blackResult;
    }

    public void setBlackResult(int blackResult) {
        this.blackResult = blackResult;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void updatePerformance(int points, boolean isWhite) {
        Player pl;
        if(isWhite){
            pl = getWhite();
            setWhiteResult(points);
            setWhiteResultRegistered(true);
        }
        else{
            pl = getBlack();
            setBlackResult(points);
            setBlackResultRegistered(true);
        }
        List<Performance> plPerformances = pl.getPerfomances();
        for(Performance plPerformance:plPerformances){
            if(plPerformance.getTournamentName().equals(tournamentName) ){
                plPerformance.setPoints(plPerformance.getPoints()+points);
                if (points == 2) {
                    plPerformance.setNumWins(plPerformance.getNumWins()+1);
                } else if (points == 1) {
                    plPerformance.setNumDraws(plPerformance.getNumDraws()+1);
                } else {
                    plPerformance.setNumLosses(plPerformance.getNumLosses()+1);
                }
            }
        }

    }
}
